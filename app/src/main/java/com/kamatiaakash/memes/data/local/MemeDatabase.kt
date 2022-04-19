package com.kamatiaakash.memes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MemeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MemeDatabase : RoomDatabase() {

    abstract val dao: MemeDao
}