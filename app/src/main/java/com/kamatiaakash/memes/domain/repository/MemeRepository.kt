package com.kamatiaakash.memes.domain.repository

import com.kamatiaakash.memes.domain.model.Meme
import com.kamatiaakash.memes.util.Resource
import kotlinx.coroutines.flow.Flow

interface MemeRepository {

    suspend fun getMemeList(fetchFromRemote:Boolean):Flow<Resource<List<Meme>>>
}