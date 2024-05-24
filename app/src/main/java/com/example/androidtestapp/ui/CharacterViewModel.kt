package com.example.androidtestapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestapp.data.CharacterDatabase
import com.example.androidtestapp.data.CharacterRepository
import com.example.androidtestapp.api.RetrofitClient
import com.example.androidtestapp.model.Character
import com.example.androidtestapp.model.CharacterResponse
import com.example.androidtestapp.util.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Sealed class to represent the UI state.
 */
sealed class UiState {
    data object Loading : UiState() // Represents a loading state.
    data class Success(val characters: List<Character>) : UiState() // Represents a successful state with a list of characters.
    data class Error(val message: String) : UiState() // Represents an error state with an error message.
}

/**
 * ViewModel class for managing character data and UI state.
 *
 * @param application The Application instance.
 */
class CharacterViewModel(private val application: Application) : AndroidViewModel(application) {
    // DAO for accessing character data from the database
    private val characterDao = CharacterDatabase.getDatabase(application).characterDao()
    // Repository for handling character data operations
    private val repository = CharacterRepository(characterDao)
    // MutableStateFlow for tracking the next page number
    private val nextPage: MutableStateFlow<Int> = MutableStateFlow(1)
    // Boolean to track if the last page has been reached
    private var isLastPage: Boolean = false
    // MutableStateFlow for UI state
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    // Public StateFlow to expose UI state
    val uiState: StateFlow<UiState> get() = _uiState

    init {
        fetchCharacters() // Initial fetch of characters
    }

    /**
     * Fetches the first page of characters.
     */
    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading // Set UI state to loading
                nextPage.value = 1 // Reset next page to 1
                val characters = getCharacters { RetrofitClient.apiService.getCharacters() } // Fetch characters from API
                _uiState.value = UiState.Success(characters) // Update UI state with fetched characters
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error") // Update UI state with error message
            }
        }
    }

    /**
     * Fetches the next page of characters.
     */
    fun fetchCharactersByPage() {
        viewModelScope.launch {
            if (isLastPage) return@launch // If last page is reached, do not fetch more characters
            try {
                val currentCharacters = (_uiState.value as? UiState.Success)?.characters ?: emptyList() // Get current characters from UI state
                val characters = getCharacters { RetrofitClient.apiService.charactersByPage(nextPage.value) } // Fetch next page of characters from API
                _uiState.value = UiState.Success(currentCharacters + characters) // Update UI state with current and new characters
            } catch (e: Exception) {
                println(e)
                _uiState.value = UiState.Error(e.message ?: "Unknown error") // Update UI state with error message
            }
        }
    }

    /**
     * Fetches characters from the API or database depending on network availability.
     *
     * @param callRequest A suspend function that makes the API call.
     * @return A list of characters.
     */
    private suspend fun getCharacters(callRequest: suspend () -> CharacterResponse): List<Character> {
        return if (NetworkUtils.isOnline(application)) {
            // If online, fetch characters from API
            val response = callRequest()
            nextPage.value += 1 // Increment next page number
            isLastPage = response.info.pages < nextPage.value // Check if last page is reached
            repository.insertAll(response.results) // Save fetched characters to database
            response.results // Return fetched characters
        } else {
            // If offline, fetch characters from database
            isLastPage = true // Set last page to true as no more pages will be fetched
            repository.getAllCharacters() // Return characters from database
        }
    }
}