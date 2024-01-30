package com.example.domain

import com.example.data.model.entities.ArticleEntity
import com.example.data.repository.DataRepositoryImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetArticleByUrlUseCaseTest {

    private lateinit var dataRepository: DataRepositoryImpl

    private lateinit var getArticleByUrlUseCase: GetArticleByUrlUseCase

    @Before
    fun setUp(){
        dataRepository = mockk()
        getArticleByUrlUseCase = spyk(GetArticleByUrlUseCase(dataRepository))
    }

    @Test
    fun `test use case`() = runTest {
        val url = "url"
        val exampleArt = ArticleEntity(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage",
            queryTopicIndex = 1
        )

        coEvery { dataRepository.getArticleByUrl(url) } returns Result.success(exampleArt)

        val actual = getArticleByUrlUseCase(url)

        coVerify { dataRepository.getArticleByUrl(url) }

        val expected = Result.success(exampleArt.toArticle())
        assertEquals(expected, actual)
    }

    @After
    fun finish(){
        clearAllMocks()
    }
}