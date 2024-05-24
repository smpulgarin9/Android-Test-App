package com.example.androidtestapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidtestapp.model.CharacterEntity

/**
 * CharacterDatabase is the main database for storing character data.
 * It provides an instance of the CharacterDao for database operations.
 */
@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    /**
     * Provides the DAO for accessing character data.
     *
     * @return Instance of [CharacterDao].
     */
    abstract fun characterDao(): CharacterDao

    companion object {
        // Singleton instance of CharacterDatabase to prevent multiple instances of the database
        // opening at the same time.
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        /**
         * Gets the singleton instance of CharacterDatabase.
         * If the instance does not already exist, it will be created.
         *
         * @param context Application context.
         * @return Singleton instance of [CharacterDatabase].
         */
        fun getDatabase(context: Context): CharacterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "app_database" // Name of the database file.
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
