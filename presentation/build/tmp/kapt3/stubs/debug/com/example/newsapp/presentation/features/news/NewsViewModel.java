package com.example.newsapp.presentation.features.news;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u000eR&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0016"}, d2 = {"Lcom/example/newsapp/presentation/features/news/NewsViewModel;", "Landroidx/lifecycle/ViewModel;", "getLatestNewsFlowUseCase", "Lcom/example/domain/GetLatestNewsFlowUseCase;", "(Lcom/example/domain/GetLatestNewsFlowUseCase;)V", "dataFLow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/example/domain/Article;", "getDataFLow", "()Lkotlinx/coroutines/flow/Flow;", "setDataFLow", "(Lkotlinx/coroutines/flow/Flow;)V", "lastQuery", "Lcom/example/common/QueryTopic;", "getLastQuery", "()Lcom/example/common/QueryTopic;", "setLastQuery", "(Lcom/example/common/QueryTopic;)V", "fetchData", "Lkotlinx/coroutines/Job;", "queryTopic", "presentation_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class NewsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.domain.GetLatestNewsFlowUseCase getLatestNewsFlowUseCase = null;
    @org.jetbrains.annotations.NotNull
    private kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.example.domain.Article>> dataFLow;
    @org.jetbrains.annotations.NotNull
    private com.example.common.QueryTopic lastQuery;
    
    @javax.inject.Inject
    public NewsViewModel(@org.jetbrains.annotations.NotNull
    com.example.domain.GetLatestNewsFlowUseCase getLatestNewsFlowUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.example.domain.Article>> getDataFLow() {
        return null;
    }
    
    public final void setDataFLow(@org.jetbrains.annotations.NotNull
    kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.example.domain.Article>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.common.QueryTopic getLastQuery() {
        return null;
    }
    
    public final void setLastQuery(@org.jetbrains.annotations.NotNull
    com.example.common.QueryTopic p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.Job fetchData(@org.jetbrains.annotations.NotNull
    com.example.common.QueryTopic queryTopic) {
        return null;
    }
}