package com.benallouch.yunar.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsSourceDbEntity(
        @PrimaryKey(autoGenerate = false) val id: String?,
        var name: String

)