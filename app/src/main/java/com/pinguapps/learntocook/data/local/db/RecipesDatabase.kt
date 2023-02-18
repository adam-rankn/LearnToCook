package com.pinguapps.learntocook.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pinguapps.learntocook.data.local.model.Recipe

@Database(
    entities = [Recipe::class],
    version = 1
)
abstract class RecipesDatabase: RoomDatabase() {
    abstract fun getItemsDao(): RecipesDao
}