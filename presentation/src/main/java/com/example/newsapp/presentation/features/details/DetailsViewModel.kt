package com.example.newsapp.presentation.features.details

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.common.ErrorType
import com.example.domain.Article
import com.example.domain.GetArticleByUrlUseCase
import com.example.newsapp.MainActivity
import com.example.newsapp.presentation.components.UiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    private val getArticleByUrlUseCase: GetArticleByUrlUseCase,
    @Assisted private val articleUrl: String?
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Article>>(UiState.Loading())
    val uiState: StateFlow<UiState<Article>> = _uiState

    init {
        loadImage()
    }

    private fun loadImage() = viewModelScope.launch {
        if (articleUrl == null)
            _uiState.value = UiState.Failure(ErrorType.EmptyResult)
        else {
            val article = getArticleByUrlUseCase.invoke(articleUrl)
            _uiState.value = if (article == null)
                UiState.Failure(ErrorType.EmptyResult)
            else
                UiState.Success(article)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(articleUrl: String?) : DetailsViewModel
    }

    companion object {
        fun provideFactory(factory: Factory, articleUrl: String?) : ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(articleUrl) as T
                }
            }
        }
    }
}

@Composable
fun detailViewModel(articleUrl: String): DetailsViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).detailViewModelFactory()

    return viewModel(factory = DetailsViewModel.provideFactory(factory, articleUrl))
}

