package com.example.androidtestapp.data

import com.example.androidtestapp.model.Character
import com.example.androidtestapp.model.CharacterEntity
import com.example.androidtestapp.model.CharacterLocation

/**
 * Repository class for handling data operations related to characters.
 * Acts as a mediator between different data sources (e.g., database, network).
 *
 * @param characterDao Data Access Object for character-related database operations.
 */
class CharacterRepository(private val characterDao: CharacterDao) {

    /**
     * Inserts a list of characters into the database.
     * Maps the domain model [Character] to the database entity model [CharacterEntity] before insertion.
     *
     * @param characters List of [Character] objects to insert.
     */
    suspend fun insertAll(characters: List<Character>) {
        characterDao.insertAll(characters.map { CharacterEntity(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            image = it.image,
            gender = it.gender,
            origin = it.origin.name,
            location = it.location.name
        ) })
    }

    /**
     * Retrieves all characters from the database.
     * Maps the database entity model [CharacterEntity] to the domain model [Character].
     *
     * @return List of [Character] objects.
     */
    suspend fun getAllCharacters(): List<Character> {
        val characters = characterDao.getAllCharacters()
        return characters.map { Character(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            image = it.image,
            gender = it.gender,
            origin = CharacterLocation(it.origin),
            location = CharacterLocation(it.location)
        )}
    }

    /**
     * Deletes all characters from the database.
     */
    suspend fun deleteAll() {
        characterDao.deleteAll()
    }
}
