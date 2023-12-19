package com.example.newsappcompose.domain.usecase.local_usecase

import com.example.newsappcompose.domain.repository.NewsRepository
import javax.inject.Inject

class SaveBookMarkUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {


    suspend operator fun invoke() {
        newsRepository.bookmark()
    }
}