package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.entities.ArticleEntity

@Database(entities = [ArticleEntity::class], exportSchema = false, version = 2)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun provideDao(): LocalDao
}