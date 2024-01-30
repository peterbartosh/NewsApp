package com.example.data

import com.example.common.QueryTopic
import com.example.data.components.toArticleEntity
import com.example.data.model.dto.RemoteArticle
import com.example.data.model.dto.Source
import com.example.data.model.entities.ArticleEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test


class ConverterTest {

    @Test
    fun `test RemoteArticle to ArticleEntity converting method`() {
        val queryTopic = QueryTopic.entries[0]

        val correctRemoteArticle = RemoteArticle(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage",
            content = "some content",
            source = Source("id", "name")
        )

        val correctExpected = ArticleEntity(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage",
            queryTopicIndex = queryTopic.ordinal
        )

        val incorrectRemoteArticle = correctRemoteArticle.copy(title = null)

        val incorrectExpected: ArticleEntity? = null

        assertEquals(correctExpected, correctRemoteArticle.toArticleEntity(queryTopic))
        assertEquals(incorrectExpected, incorrectRemoteArticle.toArticleEntity(queryTopic))
    }
}