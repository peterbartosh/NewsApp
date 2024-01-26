package com.example.newsapp.presentation.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.ErrorType
import com.example.common.UiState
import com.example.domain.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getLocalArticleByUrlUseCase: com.example.domain.GetLocalArticleByUrlUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Article>>(UiState.Loading())
    val uiState: StateFlow<UiState<Article>> = _uiState

    fun loadImage(articleUrl: String?) = viewModelScope.launch {
            if (articleUrl == null)
                _uiState.value = UiState.Failure(ErrorType.EmptyResult)
            else {
                val article = getLocalArticleByUrlUseCase.invoke(articleUrl)
                _uiState.value = if (article == null)
                    UiState.Failure(ErrorType.EmptyResult)
                else
                    UiState.Success(article)
            }
        }
    }

