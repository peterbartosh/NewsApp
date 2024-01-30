package com.example.data

import com.example.common.CustomException
import com.example.common.ErrorType
import com.example.common.QueryTopic
import com.example.data.model.common.DataArticle
import com.example.data.model.dto.RemoteArticle
import com.example.data.model.dto.Source
import com.example.data.model.dto.SuccessfulResponse
import com.example.data.model.entities.ArticleEntity
import com.example.data.repository.DataRepository
import com.example.data.repository.DataRepositoryImpl
import com.example.data.source.LocalSource
import com.example.data.source.RemoteSource
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


class RepositoryTest {

    private lateinit var dataRepository: DataRepository
    private lateinit var localSource: LocalSource
    private lateinit var remoteSource: RemoteSource

    private val queryTopic = QueryTopic.entries[0]
    private val page = 1

    private val responseDataExample =
        RemoteArticle(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage",
            source = Source(id = "id", name = "name"),
            content = "content"
        )

    private val localDataExample =
        ArticleEntity(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage",
            queryTopicIndex = queryTopic.ordinal
        )

    @Before
    fun setUp() {
        localSource = mockk()
        remoteSource = mockk()
        dataRepository = spyk(DataRepositoryImpl(localSource, remoteSource))
    }

    @Test
    fun `test getArticles with network error`() = runTest {

        coEvery { remoteSource.getLatestNews(queryTopic, page) } returns Result.failure(CustomException(ErrorType.Connection, 403))
        coEvery { localSource.getArticles(queryTopic, page) } returns emptyList()

        val result = dataRepository.getArticles(page, queryTopic)

        coVerify { dataRepository.getArticles(page, queryTopic) }

        val expectedResult: List<DataArticle>? = null
        val actualResult = result.getOrNull()

        val expectedException = CustomException(ErrorType.Connection, 403)
        val actualException = result.exceptionOrNull()

        assertEquals(expectedResult, actualResult)
        assertEquals(expectedException, actualException)
    }

    @Test
    fun `test getArticles with network error and cached local data`() = runTest {
        val cachedData = listOf(localDataExample)
        coEvery { remoteSource.getLatestNews(queryTopic, page) } returns Result.failure(CustomException(ErrorType.Connection, 403))
        coEvery { localSource.getArticles(queryTopic, page) } returns cachedData

        val result = dataRepository.getArticles(page, queryTopic)

        coVerify { dataRepository.getArticles(page, queryTopic) }

        val expectedResult = cachedData
        val actualResult = result.getOrNull()

        val expectedException: Throwable? = null
        val actualException = result.exceptionOrNull()

        assertEquals(expectedResult, actualResult)
        assertEquals(expectedException, actualException)
    }

    @Test
    fun `test getArticles with normal api response`() = runTest {

        val responseData = listOf(responseDataExample)
        val cachedData = listOf(localDataExample)

        coEvery { remoteSource.getLatestNews(queryTopic, page) } returns Result.success(
            SuccessfulResponse(articles = responseData, status = "200", totalResults = 1)
        )
        coEvery { localSource.getArticles(queryTopic, page) } returns cachedData
        coEvery { localSource.clearAllByQuery(queryTopic) } returns Unit
        coEvery { localSource.insertAll(any()) } returns Unit

        val result = dataRepository.getArticles(page, queryTopic)

        coVerify { dataRepository.getArticles(page, queryTopic) }

        val expectedResult = responseData
        val actualResult = result.getOrNull()

        val expectedException: Throwable? = null
        val actualException = result.exceptionOrNull()

        assertEquals(expectedResult, actualResult)
        assertEquals(expectedException, actualException)
    }

    @Test
    fun `test getArticleByUrl with wrong url`() = runTest {
        val wrongUrl = "wrong"

        coEvery { localSource.getArticle(wrongUrl) } returns null

        val result = dataRepository.getArticleByUrl(wrongUrl)

        coVerify { dataRepository.getArticleByUrl(wrongUrl) }

        val expectedResult: DataArticle? = null
        val actualResult = result.getOrNull()

        val expectedException = CustomException(ErrorType.EmptyResult, -1)
        val actualException = result.exceptionOrNull()

        assertEquals(expectedResult, actualResult)
        assertEquals(expectedException, actualException)
    }

    @Test
    fun `test getArticleByUrl with correct url`() = runTest {
        val correctUrl = "correct"

        coEvery { localSource.getArticle(correctUrl) } returns localDataExample

        val result = dataRepository.getArticleByUrl(correctUrl)

        coVerify { dataRepository.getArticleByUrl(correctUrl) }

        val expectedResult: DataArticle = localDataExample
        val actualResult = result.getOrNull()

        val expectedException: Throwable? = null
        val actualException = result.exceptionOrNull()

        assertEquals(expectedResult, actualResult)
        assertEquals(expectedException, actualException)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

}