package com.example.newsappcompose.domain.di

import android.content.Context
import androidx.room.Room
import com.example.newsappcompose.data.local.NewsDatabase
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.repository.NewsRepository
import com.example.newsappcompose.domain.usecase.AllUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.AddArticleUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.DeleteArticleUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.GetAllArticleUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.ReadBookMarkUseCase
import com.example.newsappcompose.domain.usecase.remote_usecase.GetNewsUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.ReadUserEntryUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.RemoveBookMarkUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.SaveBookMarkUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.SaveUserEntryUseCase
import com.example.newsappcompose.domain.usecase.remote_usecase.SearchNewsUseCase
import com.example.newsappcompose.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // this decide about the hole life time
object NewsModule {


    @Provides
    @Singleton // this decide about how many instance we need
    fun provideOkHttpClientInstance(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabaseInstance(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }



    @Provides
    @Singleton
    fun provideNewsRepositoryInstance(
        @ApplicationContext context: Context,
        newsApi: NewsApi,
        newsDatabase: NewsDatabase
    ): NewsRepository {
        return NewsRepositoryImpl(context, newsApi, newsDatabase)
    }


    @Provides
    @Singleton
    fun provideAllUseCases(
        newsRepository: NewsRepository,
    ): AllUseCase {
        return AllUseCase(
            saveUserEntryUseCase = SaveUserEntryUseCase(newsRepository),
            readUserEntryUseCase = ReadUserEntryUseCase(newsRepository),
            getNewsUseCase = GetNewsUseCase(newsRepository),
            searchNewsUseCase = SearchNewsUseCase(newsRepository),
            addArticleUseCase = AddArticleUseCase(newsRepository),
            deleteArticleUseCase = DeleteArticleUseCase(newsRepository),
            getAllArticleUseCase = GetAllArticleUseCase(newsRepository),
            saveBookMark = SaveBookMarkUseCase(newsRepository),
            readBookMarkUseCase = ReadBookMarkUseCase(newsRepository),
            removeBookMarkUseCase = RemoveBookMarkUseCase(newsRepository)
        )
    }

}














