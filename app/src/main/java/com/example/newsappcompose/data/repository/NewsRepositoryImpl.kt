package com.example.newsappcompose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.data.local.NewsDatabase
import com.example.newsappcompose.data.paging.NewsPaginationSource
import com.example.newsappcompose.data.paging.SearchNewsPagingSource
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import com.example.newsappcompose.utils.Constant.USER_ENTRY
import com.example.newsappcompose.utils.Constant.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val context: Context,
    private val newsApi: NewsApi,
    private val newsDao: NewsDatabase
): NewsRepository {

    override suspend fun saveUserEntry() {
        context.datastore.edit { settings ->
            settings[PreferencesKey.userEntry] = true
        }
    }

    override fun readUserEntry(): Flow<Boolean> {
        return context.datastore.data.map { preference ->
            preference[PreferencesKey.userEntry] ?: false
        }
    }

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPaginationSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(sources: List<String>, query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory =  {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ","),
                    query = query
                )
            }
        ).flow
    }

    override suspend fun addArticle(article: Article) {
        newsDao.newsDao.addArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.newsDao.deleteArticle(article)
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return newsDao.newsDao.getAllArticles()
    }
}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)

private object PreferencesKey {
    val userEntry = booleanPreferencesKey(name = USER_ENTRY)
}



