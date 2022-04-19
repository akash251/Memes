package com.kamatiaakash.memes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemeList(meme: List<MemeEntity>)

    @Query("DELETE FROM MemeEntity")
    suspend fun deleteMemeList()

    @Query("SELECT * FROM MemeEntity")
    suspend fun getStoredMemeList(): List<MemeEntity>
}