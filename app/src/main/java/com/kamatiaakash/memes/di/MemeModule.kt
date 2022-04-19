package com.kamatiaakash.memes.di

import android.app.Application
import androidx.room.Room
import com.kamatiaakash.memes.data.local.MemeDatabase
import com.kamatiaakash.memes.data.remote.MemeApi
import com.kamatiaakash.memes.data.repository.MemeRepositoryImpl
import com.kamatiaakash.memes.domain.repository.MemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MemeModule {

    @Provides
    @Singleton
    fun provideMemeApi(): MemeApi {
        return Retrofit.Builder().baseUrl(MemeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MemeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMemeDatabase(app: Application): MemeDatabase {
        return Room.databaseBuilder(
            app,
            MemeDatabase::class.java,
            "meme_list_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMemeRepository(api: MemeApi, db: MemeDatabase): MemeRepository {
        return MemeRepositoryImpl(api = api, db = db)
    }
}

