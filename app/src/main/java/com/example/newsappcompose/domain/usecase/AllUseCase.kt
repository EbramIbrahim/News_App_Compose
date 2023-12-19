package com.example.newsappcompose.domain.usecase

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

data class AllUseCase(
    val readUserEntryUseCase: ReadUserEntryUseCase,
    val saveUserEntryUseCase: SaveUserEntryUseCase,
    val getNewsUseCase: GetNewsUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val addArticleUseCase: AddArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getAllArticleUseCase: GetAllArticleUseCase,
    val saveBookMark: SaveBookMarkUseCase,
    val readBookMarkUseCase: ReadBookMarkUseCase,
    val removeBookMarkUseCase: RemoveBookMarkUseCase
)
