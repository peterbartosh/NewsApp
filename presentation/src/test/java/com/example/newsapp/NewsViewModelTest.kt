package com.example.newsapp

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import com.example.common.QueryTopic
import com.example.domain.Article
import com.example.domain.GetLatestNewsFlowUseCase
import com.example.newsapp.presentation.features.news.NewsViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class NewsViewModelTest: BaseTest() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var getLatestNewsFlowUseCase: GetLatestNewsFlowUseCase

    private val queryTopic = QueryTopic.entries[0]

    private val exampleArticle = Article(
        url = "https://real",
        author = "techcrunch.com",
        description = "description",
        publishedAt = "publishedAt",
        title = "title",
        urlToImage = "https://urlToImage"
    )

    private class TestListCallback : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }

    private class TestDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncPagingDataDiffer(
        diffCallback = TestDiffCallback<Article>(),
        updateCallback = TestListCallback(),
        workerDispatcher = Dispatchers.Main
    )

    @Before
    override fun beforeEach() {
        super.beforeEach()
        getLatestNewsFlowUseCase = mockk()
    }

    @Test
    fun `test view model state flow with correct use case output`() = runTest {
        val articles = listOf(exampleArticle, exampleArticle)

        every { getLatestNewsFlowUseCase(queryTopic) } returns flowOf(PagingData.from(articles))
        newsViewModel = spyk(NewsViewModel(getLatestNewsFlowUseCase))

        newsViewModel.fetchData(queryTopic)

        newsViewModel.dataFLow.test {
            differ.submitData(awaitItem())
            assertEquals(differ.snapshot().items, articles)
            cancelAndConsumeRemainingEvents()
            verify { getLatestNewsFlowUseCase(queryTopic) }
        }
    }

    @Test
    fun `test view model state flow with empty use case output`() = runTest {
        val articles = emptyList<Article>()

        every { getLatestNewsFlowUseCase(queryTopic) } returns flowOf(PagingData.from(articles))
        newsViewModel = spyk(NewsViewModel(getLatestNewsFlowUseCase))

        newsViewModel.fetchData(queryTopic)

        newsViewModel.dataFLow.test {
            differ.submitData(awaitItem())
            assertEquals(differ.snapshot().items, articles)
            cancelAndConsumeRemainingEvents()
            verify { getLatestNewsFlowUseCase(queryTopic) }
        }
    }
}