package com.example.newsapp.presentation.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0003\f\r\u000eB\u001f\b\u0004\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R\u0018\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0003\u000f\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/example/newsapp/presentation/components/UiState;", "T", "", "data", "errorType", "Lcom/example/common/ErrorType;", "(Ljava/lang/Object;Lcom/example/common/ErrorType;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getErrorType", "()Lcom/example/common/ErrorType;", "Failure", "Loading", "Success", "Lcom/example/newsapp/presentation/components/UiState$Failure;", "Lcom/example/newsapp/presentation/components/UiState$Loading;", "Lcom/example/newsapp/presentation/components/UiState$Success;", "presentation_debug"})
public abstract class UiState<T extends java.lang.Object> {
    @org.jetbrains.annotations.Nullable
    private final T data = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.common.ErrorType errorType = null;
    
    private UiState(T data, com.example.common.ErrorType errorType) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public T getData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public com.example.common.ErrorType getErrorType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/newsapp/presentation/components/UiState$Failure;", "T", "Lcom/example/newsapp/presentation/components/UiState;", "errorType", "Lcom/example/common/ErrorType;", "(Lcom/example/common/ErrorType;)V", "getErrorType", "()Lcom/example/common/ErrorType;", "presentation_debug"})
    public static final class Failure<T extends java.lang.Object> extends com.example.newsapp.presentation.components.UiState<T> {
        @org.jetbrains.annotations.NotNull
        private final com.example.common.ErrorType errorType = null;
        
        public Failure(@org.jetbrains.annotations.NotNull
        com.example.common.ErrorType errorType) {
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public com.example.common.ErrorType getErrorType() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/example/newsapp/presentation/components/UiState$Loading;", "T", "Lcom/example/newsapp/presentation/components/UiState;", "()V", "presentation_debug"})
    public static final class Loading<T extends java.lang.Object> extends com.example.newsapp.presentation.components.UiState<T> {
        
        public Loading() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0001\u00a2\u0006\u0002\u0010\u0004R\u0018\u0010\u0003\u001a\u0004\u0018\u00018\u0001X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\b"}, d2 = {"Lcom/example/newsapp/presentation/components/UiState$Success;", "T", "Lcom/example/newsapp/presentation/components/UiState;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "presentation_debug"})
    public static final class Success<T extends java.lang.Object> extends com.example.newsapp.presentation.components.UiState<T> {
        @org.jetbrains.annotations.Nullable
        private final T data = null;
        
        public Success(@org.jetbrains.annotations.Nullable
        T data) {
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.Nullable
        public T getData() {
            return null;
        }
        
        public Success() {
        }
    }
}