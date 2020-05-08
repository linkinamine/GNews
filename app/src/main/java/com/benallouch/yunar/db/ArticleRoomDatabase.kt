package com.benallouch.yunar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleDbEntity::class, NewsSourceDbEntity::class, TotalResultsEntity::class], version = 1)
abstract class ArticleRoomDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
                context,
                ArticleRoomDatabase::class.java,
                "articles-db"
        ).build()
    }

    abstract fun articleDao(): ArticleDao
}