package com.pinguapps.learntocook.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pinguapps.learntocook.data.local.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    // Method for inserting items into our DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: Recipe)

    // Method for getting all items from the DB
    @Query("SELECT * FROM items ORDER BY id ASC ")
    fun getAllItems(): Flow<List<Recipe>>

    // Method for searching an item from the DB
    @Query("SELECT * FROM items WHERE itemName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Recipe>>
}