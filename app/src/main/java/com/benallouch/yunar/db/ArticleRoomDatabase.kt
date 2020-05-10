package com.benallouch.yunar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.benallouch.yunar.db.entity.ArticleDbEntity
import com.benallouch.yunar.db.entity.NewsSourceDbEntity
import com.benallouch.yunar.db.entity.TotalResultsEntity

@Database(entities = [ArticleDbEntity::class, NewsSourceDbEntity::class, TotalResultsEntity::class], version = 1)
@TypeConverters(DateConverter::class)

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