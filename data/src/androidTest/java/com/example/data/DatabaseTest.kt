package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.common.QueryTopic
import com.example.data.local.LocalDao
import com.example.data.local.LocalDatabase
import com.example.data.model.entities.ArticleEntity
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var database: LocalDatabase
    private lateinit var localDao: LocalDao

    private val queryTopic = QueryTopic.entries[0]

    private val exampleArticleEntity = ArticleEntity(
        url = "https://real",
        author = "techcrunch.com",
        description = "Who knew M&A would be the thing we couldn’t shut up about?",
        publishedAt = "2024-01-24T15:40:19Z",
        title = "Who knew M&A would be the thing we couldn’t shut up about?",
        urlToImage = "https://c.biztoc.com/p/24a60a6986fdce8b/s.webp",
        queryTopicIndex = queryTopic.ordinal
    )

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            LocalDatabase::class.java
        ).allowMainThreadQueries().build()

        localDao = database.provideDao()
    }

    @Test
    fun testInsertMethod() = runBlocking {
        localDao.insertArticle(exampleArticleEntity)

        assertTrue(
            localDao
                .getArticles(queryTopicIndex = queryTopic.ordinal)
                .find { it.url == exampleArticleEntity.url } != null
        )
    }

    @Test
    fun testGetMethod() = runBlocking {
        localDao.insertArticle(exampleArticleEntity)

        val getResult = localDao.getArticle(exampleArticleEntity.url)
        assertNotNull(getResult)
        assert(
            getResult == exampleArticleEntity.copy(id = getResult?.id!!)
        )
    }

    @Test
    fun testClearMethod() = runBlocking {
        localDao.insertArticle(exampleArticleEntity)
        localDao.insertArticle(exampleArticleEntity.copy(url = "https://other_url"))

        localDao.clearAllByQuery(queryTopicIndex = queryTopic.ordinal)
        assert(
            localDao.getArticles(queryTopicIndex = queryTopic.ordinal).isEmpty()
        )
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}