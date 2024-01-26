package com.example.domain

import javax.inject.Inject

class GetLocalArticleByUrlUseCase @Inject constructor(
    private val localRepository: com.example.data.repository.LocalRepository
) {
    suspend operator fun invoke(articleUrl: String) = localRepository.getArticle(articleUrl)?.toNewsArticle()
}