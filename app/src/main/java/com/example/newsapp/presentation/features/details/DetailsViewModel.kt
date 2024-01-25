package com.example.newsapp.presentation.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.LocalRepository
import com.example.newsapp.data.utils.ErrorType
import com.example.newsapp.data.utils.UiState
import com.example.newsapp.domain.Article
import com.example.newsapp.domain.toNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Article>>(UiState.Loading())
    val uiState: StateFlow<UiState<Article>> = _uiState

    fun loadImage(articleUrl: String?) = viewModelScope.launch {
            if (articleUrl == null)
                _uiState.value = UiState.Failure(ErrorType.EmptyResult)
            else {
                val article = localRepository.getArticle(articleUrl)?.toNewsArticle()
                _uiState.value = if (article == null)
                    UiState.Failure(ErrorType.EmptyResult)
                else
                    UiState.Success(article)
            }
        }
    }

