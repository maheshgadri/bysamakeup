package com.example.makeu.di

import android.app.Application
import com.example.makeu.api.ItemService
import com.example.makeu.db.ItemDao
import com.example.makeu.db.MakeupDatabase
import com.example.makeu.utilities.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): MakeupDatabase {
        return MakeupDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun getAppDao(makeupDatabase:MakeupDatabase): ItemDao{
        return makeupDatabase.getItemDao()
    }


    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): ItemService {
        return retrofit.create(ItemService::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}