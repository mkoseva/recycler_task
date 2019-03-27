package com.example.recyclertask.di

import android.app.Application
import androidx.room.Room
import com.example.recyclertask.api.TaskApiService
import com.example.recyclertask.db.ImageDao
import com.example.recyclertask.db.TaskApiDatabase
import com.example.recyclertask.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client =
        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideTaskApiService(): TaskApiService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(TaskApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): TaskApiDatabase{
        return Room
            .databaseBuilder(app, TaskApiDatabase::class.java, "taskdatabase.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideImageDao(db: TaskApiDatabase): ImageDao{
        return db.imageDao()
    }

//    @Singleton
//    @Provides
//    fun provideRepoDao(db: GithubDb): RepoDao {
//        return db.repoDao()
//    }
}