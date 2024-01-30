package com.example.domain

import com.example.common.QueryTopic
import com.example.data.model.dto.RemoteArticle
import com.example.data.model.dto.Source
import com.example.data.model.entities.ArticleEntity
import junit.framework.TestCase
import org.junit.Test

class ConverterTest {
    @Test
    fun `test RemoteArticle to Article converting method`() {

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

        val correctExpected = Article(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage"
        )

        val incorrectRemoteArticle = correctRemoteArticle.copy(title = null)

        val incorrectExpected: Article? = null

        TestCase.assertEquals(correctExpected, correctRemoteArticle.toArticle())
        TestCase.assertEquals(incorrectExpected, incorrectRemoteArticle.toArticle())
    }

    @Test
    fun `test ArticleEntity to Article converting method`() {
        val queryTopic = QueryTopic.entries[0]

        val correctArticleEntity = ArticleEntity(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage",
            queryTopicIndex = queryTopic.ordinal
        )

        val correctExpected = Article(
            url = "https://real",
            author = "techcrunch.com",
            description = "description",
            publishedAt = "publishedAt",
            title = "title",
            urlToImage = "https://urlToImage",
        )

        val incorrectArticleEntity = correctArticleEntity.copy(title = "")

        val incorrectExpected: Article? = null

        TestCase.assertEquals(correctExpected, correctArticleEntity.toArticle())
        TestCase.assertEquals(incorrectExpected, incorrectArticleEntity.toArticle())
    }
}