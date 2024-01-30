package com.example.newsapp

import com.example.common.CustomException
import com.example.common.ErrorType
import com.example.domain.Article
import com.example.domain.GetArticleByUrlUseCase
import com.example.newsapp.presentation.components.UiState
import com.example.newsapp.presentation.features.details.DetailsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class DetailsViewModelTest: BaseTest() {

    private lateinit var detailsViewModel: DetailsViewModel

    private lateinit var getArticleByUrlUseCase: GetArticleByUrlUseCase

    @Before
    override fun beforeEach() {
        super.beforeEach()
        getArticleByUrlUseCase = mockk()
    }

    @Test
    fun `test view model with real url`() = runTest {
        val realUrl = "https://real"

        val realArticle = Article(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage"
        )

        coEvery { getArticleByUrlUseCase(realUrl) } returns Result.success(realArticle)

        detailsViewModel = spyk(DetailsViewModel(getArticleByUrlUseCase, realUrl))

        coVerify { getArticleByUrlUseCase(realUrl) }

        assertTrue(detailsViewModel.uiState.value is UiState.Success)
        assertNotNull(detailsViewModel.uiState.value.data)
        assertEquals(detailsViewModel.uiState.value.data, getArticleByUrlUseCase(realUrl).getOrNull())
    }

    @Test
    fun `test view model fake url`() = runTest {
        val fakeUrl = "https//fake"

        coEvery { getArticleByUrlUseCase(fakeUrl) } returns Result.failure(CustomException(ErrorType.EmptyResult, -1))

        detailsViewModel = spyk(DetailsViewModel(getArticleByUrlUseCase, fakeUrl))

        coVerify { getArticleByUrlUseCase(fakeUrl) }

        assertTrue(detailsViewModel.uiState.value is UiState.Failure)
        assertTrue(detailsViewModel.uiState.value.errorType === ErrorType.EmptyResult)
    }
}