package com.kamatiaakash.memes.data.repository

import com.kamatiaakash.memes.data.local.MemeDatabase
import com.kamatiaakash.memes.data.mapper.toMeme
import com.kamatiaakash.memes.data.mapper.toMemeEntity
import com.kamatiaakash.memes.data.mapper.toMemesList
import com.kamatiaakash.memes.data.remote.MemeApi
import com.kamatiaakash.memes.domain.model.Meme
import com.kamatiaakash.memes.domain.repository.MemeRepository
import com.kamatiaakash.memes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MemeRepositoryImpl @Inject constructor(
    private val api: MemeApi,
    private val db: MemeDatabase
) : MemeRepository {

    private val dao = db.dao
    override suspend fun getMemeList(fetchFromRemote: Boolean): Flow<Resource<List<Meme>>> {
        return flow {
            emit(Resource.Loading<List<Meme>>(isLoading = true))
            val localMemeList = dao.getStoredMemeList()
            emit(Resource.Success<List<Meme>>(
                data = localMemeList.map { it.toMeme() }
            ))
            val isDbEmpty = localMemeList.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading<List<Meme>>(false))
                return@flow
            }

            val remoteMemeList = try {
                val response = api.getMemeData()
                response.toMemesList().memes

            } catch (e: HttpException) {
                emit(Resource.Error<List<Meme>>(message = "Error is ${e.message}"))
                null
            } catch (e: IOException) {
                emit(Resource.Error<List<Meme>>(message = "Error is ${e.message}"))
                null
            }
            remoteMemeList?.let { meme ->
                dao.deleteMemeList()
                dao.insertMemeList(meme.map { it.toMemeEntity() })
                emit(Resource.Success<List<Meme>>(dao.getStoredMemeList().map { it.toMeme() }))
                emit(Resource.Loading<List<Meme>>(isLoading = false))
            }
        }
    }
}