package com.example.newsappcompose.domain.usecase.local_usecase

import com.example.newsappcompose.domain.repository.NewsRepository

class SaveUserEntryUseCase(
    private val repository: NewsRepository
) {

    suspend operator fun invoke() {
        repository.saveUserEntry()
    }

}






