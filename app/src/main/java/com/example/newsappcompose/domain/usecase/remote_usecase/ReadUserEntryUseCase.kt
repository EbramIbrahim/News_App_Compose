package com.example.newsappcompose.domain.usecase.remote_usecase

import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class ReadUserEntryUseCase(
    private val repository: NewsRepository
) {

     operator fun invoke(): Flow<Boolean> {
        return repository.readUserEntry()
    }

}






