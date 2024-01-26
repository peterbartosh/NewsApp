package com.example.newsapp.presentation.features.news.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QueryButton(
    isSelected: Boolean,
    query: String,
    onClick: () -> Unit
) {

    Button(
        modifier = Modifier
            .height(40.dp)
            .wrapContentHeight()
            .padding(horizontal = 5.dp),
        onClick = onClick,
        contentPadding = PaddingValues(5.dp),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        elevation = ButtonDefaults.buttonElevation(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.background
        )
    ) {

        Text(
            modifier = Modifier.padding(5.dp),
            text = query,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

    }

}