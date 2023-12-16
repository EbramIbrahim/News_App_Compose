package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.domain.usecase.local_usecase.AddArticleUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.DeleteArticleUseCase
import com.example.newsappcompose.domain.usecase.local_usecase.GetAllArticleUseCase
import com.example.newsappcompose.domain.usecase.remote_usecase.GetNewsUseCase
import com.example.newsappcompose.domain.usecase.remote_usecase.ReadUserEntryUseCase
import com.example.newsappcompose.domain.usecase.remote_usecase.SaveUserEntryUseCase
import com.example.newsappcompose.domain.usecase.remote_usecase.SearchNewsUseCase

data class AllUseCase(
    val readUserEntryUseCase: ReadUserEntryUseCase,
    val saveUserEntryUseCase: SaveUserEntryUseCase,
    val getNewsUseCase: GetNewsUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val addArticleUseCase: AddArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getAllArticleUseCase: GetAllArticleUseCase
)
