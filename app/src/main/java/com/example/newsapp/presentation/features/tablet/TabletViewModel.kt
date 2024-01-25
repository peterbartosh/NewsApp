package com.example.newsapp.presentation.features.tablet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TabletViewModel @Inject constructor(): ViewModel(){
    var selectedArticleUrl by mutableStateOf<String?>(null)
    var selectedArticleIndex by mutableStateOf(-1)
}