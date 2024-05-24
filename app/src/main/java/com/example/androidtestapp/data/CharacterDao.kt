package com.example.androidtestapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtestapp.model.CharacterEntity

/**
 * Data Access Object (DAO) for the Character database.
 * Defines methods for interacting with the character data in the database.
 */
@Dao
interface CharacterDao {

    /**
     * Inserts a list of characters into the database.
     * If a conflict occurs (e.g., a character with the same primary key already exists),
     * the existing character will be replaced.
     *
     * @param characters List of [CharacterEntity] objects to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    /**
     * Retrieves all characters from the database.
     *
     * @return List of [CharacterEntity] objects.
     */
    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>

    /**
     * Deletes all characters from the database.
     */
    @Query("DELETE FROM characters")
    suspend fun deleteAll()
}
