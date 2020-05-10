package com.benallouch.yunar.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * We persist the total items in case we wanted to implement pagination in the locally fetched results
 */
@Entity
data class TotalResultsEntity(
        @PrimaryKey val id: String,
        var totalResults: Int
)