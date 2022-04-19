package com.kamatiaakash.memes.data.remote

import com.kamatiaakash.memes.data.remote.dto.MemesListDto
import retrofit2.http.GET

interface MemeApi {


    @GET("/gimme/50")
    suspend fun getMemeData(): MemesListDto


    companion object {
        const val BASE_URL = "https://meme-api.herokuapp.com/"
    }
}