package com.example.newsapp.presentation.features.details;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lcom/example/newsapp/presentation/features/details/DetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "getArticleByUrlUseCase", "Lcom/example/domain/GetArticleByUrlUseCase;", "articleUrl", "", "(Lcom/example/domain/GetArticleByUrlUseCase;Ljava/lang/String;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/newsapp/presentation/components/UiState;", "Lcom/example/domain/Article;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadImage", "Lkotlinx/coroutines/Job;", "Companion", "Factory", "presentation_debug"})
public final class DetailsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.domain.GetArticleByUrlUseCase getArticleByUrlUseCase = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String articleUrl = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.newsapp.presentation.components.UiState<com.example.domain.Article>> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.newsapp.presentation.components.UiState<com.example.domain.Article>> uiState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.newsapp.presentation.features.details.DetailsViewModel.Companion Companion = null;
    
    @dagger.assisted.AssistedInject
    public DetailsViewModel(@org.jetbrains.annotations.NotNull
    com.example.domain.GetArticleByUrlUseCase getArticleByUrlUseCase, @dagger.assisted.Assisted
    @org.jetbrains.annotations.Nullable
    java.lang.String articleUrl) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.newsapp.presentation.components.UiState<com.example.domain.Article>> getUiState() {
        return null;
    }
    
    private final kotlinx.coroutines.Job loadImage() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a8\u0006\t"}, d2 = {"Lcom/example/newsapp/presentation/features/details/DetailsViewModel$Companion;", "", "()V", "provideFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "factory", "Lcom/example/newsapp/presentation/features/details/DetailsViewModel$Factory;", "articleUrl", "", "presentation_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.lifecycle.ViewModelProvider.Factory provideFactory(@org.jetbrains.annotations.NotNull
        com.example.newsapp.presentation.features.details.DetailsViewModel.Factory factory, @org.jetbrains.annotations.Nullable
        java.lang.String articleUrl) {
            return null;
        }
    }
    
    @dagger.assisted.AssistedFactory
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/newsapp/presentation/features/details/DetailsViewModel$Factory;", "", "create", "Lcom/example/newsapp/presentation/features/details/DetailsViewModel;", "articleUrl", "", "presentation_debug"})
    public static abstract interface Factory {
        
        @org.jetbrains.annotations.NotNull
        public abstract com.example.newsapp.presentation.features.details.DetailsViewModel create(@org.jetbrains.annotations.Nullable
        java.lang.String articleUrl);
    }
}