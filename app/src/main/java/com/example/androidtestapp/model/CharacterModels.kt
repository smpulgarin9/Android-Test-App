package com.example.androidtestapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing the response from the API containing a list of characters and pagination information.
 *
 * @param results List of [Character] objects.
 * @param info Pagination information.
 */
data class CharacterResponse(
    val results: List<Character>,
    val info: RequestInfo
)

/**
 * Data class representing a character.
 *
 * @param id Unique identifier for the character.
 * @param name Name of the character.
 * @param status Status of the character (e.g., Alive, Dead, Unknown).
 * @param species Species of the character.
 * @param image URL of the character's image.
 * @param gender Gender of the character.
 * @param origin Location of origin of the character.
 * @param location Current location of the character.
 */
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String,
    val origin: CharacterLocation,
    val location: CharacterLocation
)

/**
 * Data class representing a location associated with a character.
 *
 * @param name Name of the location.
 */
data class CharacterLocation(
    val name: String
)

/**
 * Data class representing pagination information from the API.
 *
 * @param pages Total number of pages available.
 */
data class RequestInfo(
    val pages: Int
)

/**
 * Room entity representing a character in the local database.
 *
 * @param id Unique identifier for the character (same as API).
 * @param name Name of the character.
 * @param status Status of the character (e.g., Alive, Dead, Unknown).
 * @param species Species of the character.
 * @param image URL of the character's image.
 * @param gender Gender of the character.
 * @param origin Name of the location of origin of the character.
 * @param location Name of the current location of the character.
 */
@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String,
    val origin: String,
    val location: String
)
