package com.example.androidtestapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidtestapp.model.Character
import com.example.androidtestapp.ui.CharacterViewModel

/**
 * Composable function to display a list of characters with a pagination indicator.
 *
 * @param characters The list of [Character] objects to display.
 * @param viewModel The [CharacterViewModel] used to fetch additional characters when pagination is triggered.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterListContent(characters: List<Character>, viewModel: CharacterViewModel) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        // Sticky header for the list
        stickyHeader { HeaderItem() }

        // Display each character item
        items(characters) { character ->
            CharacterItem(character)
        }

        // Pagination Item
        item {
            // Display a circular progress indicator to indicate loading more items
            CircularProgressIndicator()
            LaunchedEffect(Unit) {
                // Fetch the next page of characters when this item is displayed
                viewModel.fetchCharactersByPage()
            }
        }
    }
}

/**
 * Composable function to display an error message.
 *
 * @param message The error message to display.
 */
@Composable
fun ErrorMessage(message: String) {
    Text(
        text = message,
        color = Color.Red,
        modifier = Modifier.padding(16.dp)
    )
}

/**
 * Composable function to display Header of list.
 */
@Composable
fun HeaderItem() {
    Text(
        text = "Characters",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
    Divider()
}
