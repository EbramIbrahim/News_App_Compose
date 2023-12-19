package com.example.newsappcompose.domain.usecase.local_usecase

import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadBookMarkUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {


     operator fun invoke(): Flow<Boolean> {
       return newsRepository.readBookMark()
    }
}