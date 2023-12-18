package com.example.newsapp.presentation.features.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.data.utils.browse
import com.example.newsapp.data.utils.formatDateString
import com.example.newsapp.domain.Article


@Composable
fun SucceedScreenData(article: Article) {

    val context = LocalContext.current

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            //.heightIn(max = 700.dp)
            .padding(vertical = 5.dp),
        model = article.urlToImage,
        contentScale = ContentScale.FillWidth,
        contentDescription = null
    )

    Text(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        text = article.title,
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )

    Divider(Modifier.padding(vertical = 5.dp))


    Text(
        modifier = Modifier
            .wrapContentHeight(),
        text = article.author + " (" + formatDateString(article.publishedAt) + ")",
        textAlign = TextAlign.Center,
        fontSize = 11.sp,
        fontFamily = FontFamily.SansSerif,
        color = MaterialTheme.colorScheme.onBackground
    )

    Divider(Modifier.padding(vertical = 5.dp))

    val annotatedString = getBrowseAnnotatedString(description = article.description, url = article.url)

    SelectionContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
    ) {
        ClickableText(
            text = getBrowseAnnotatedString(description = article.description, url = article.url),
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "web link", start = offset, end = offset).firstOrNull()?.let {
                    context.browse(it.item)
                }
            }
        )
    }
}

@Composable
private fun getBrowseAnnotatedString(description: String, url: String) = buildAnnotatedString {
    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp)) {
        append(description)
    }
    pushStringAnnotation(tag = "web link", annotation = url)
    withStyle(style = SpanStyle(color = Color.Blue)) {
        append(" (browse)")
    }
    pop()
}