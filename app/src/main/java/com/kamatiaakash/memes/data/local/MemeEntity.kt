package com.kamatiaakash.memes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MemeEntity(
    @PrimaryKey val id: Int? = null,
    val author: String,
    val postLink: String,
    val title: String,
    val url: String
)