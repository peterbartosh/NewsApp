package com.example.domain

import com.example.data.repository.DataRepository
import javax.inject.Inject

class GetArticleByUrlUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {

    suspend operator fun invoke(articleUrl: String) =
        dataRepository.getArticleByUrl(articleUrl).map { it.toArticle() }

}