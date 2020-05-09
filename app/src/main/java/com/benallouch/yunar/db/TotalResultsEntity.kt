package com.benallouch.yunar.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TotalResultsEntity(
        @PrimaryKey val id: String,
        var totalResults: Int
)