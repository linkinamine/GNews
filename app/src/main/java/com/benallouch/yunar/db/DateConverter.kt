package com.benallouch.yunar.db

import androidx.room.TypeConverter
import java.util.*

/**
 * Mandatory to persist date objects in [ArticleRoomDatabase]
 */
class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}