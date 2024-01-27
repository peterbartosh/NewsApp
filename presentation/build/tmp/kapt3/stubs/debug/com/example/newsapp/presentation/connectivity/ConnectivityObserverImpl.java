package com.example.newsapp.presentation.connectivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/newsapp/presentation/connectivity/ConnectivityObserverImpl;", "Lcom/example/newsapp/presentation/connectivity/ConnectivityObserver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "connectivityManager", "Landroid/net/ConnectivityManager;", "isUserConnected", "", "observe", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/newsapp/presentation/connectivity/ConnectivityObserver$Status;", "presentation_debug"})
public final class ConnectivityObserverImpl implements com.example.newsapp.presentation.connectivity.ConnectivityObserver {
    @org.jetbrains.annotations.NotNull
    private final android.net.ConnectivityManager connectivityManager = null;
    
    public ConnectivityObserverImpl(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override
    public boolean isUserConnected() {
        return false;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.example.newsapp.presentation.connectivity.ConnectivityObserver.Status> observe() {
        return null;
    }
}