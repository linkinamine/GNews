package com.benallouch.yunar.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsSourceDbEntity(
        @PrimaryKey(autoGenerate = false) var newsSourceId: String,
        var name: String

)