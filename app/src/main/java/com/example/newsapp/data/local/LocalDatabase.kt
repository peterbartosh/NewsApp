package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.model.entities.ArticleEntity

@Database(entities = [ArticleEntity::class], exportSchema = false, version = 2)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun provideDao(): LocalDao
}