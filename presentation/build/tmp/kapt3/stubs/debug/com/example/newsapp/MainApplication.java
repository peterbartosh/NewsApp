package com.example.newsapp;

@dagger.hilt.android.HiltAndroidApp
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lcom/example/newsapp/MainApplication;", "Landroid/app/Application;", "Lcoil/ImageLoaderFactory;", "()V", "connectivityObserver", "Lcom/example/newsapp/presentation/connectivity/ConnectivityObserverImpl;", "getConnectivityObserver", "()Lcom/example/newsapp/presentation/connectivity/ConnectivityObserverImpl;", "setConnectivityObserver", "(Lcom/example/newsapp/presentation/connectivity/ConnectivityObserverImpl;)V", "newImageLoader", "Lcoil/ImageLoader;", "onCreate", "", "presentation_debug"})
public final class MainApplication extends android.app.Application implements coil.ImageLoaderFactory {
    public com.example.newsapp.presentation.connectivity.ConnectivityObserverImpl connectivityObserver;
    
    public MainApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.newsapp.presentation.connectivity.ConnectivityObserverImpl getConnectivityObserver() {
        return null;
    }
    
    public final void setConnectivityObserver(@org.jetbrains.annotations.NotNull
    com.example.newsapp.presentation.connectivity.ConnectivityObserverImpl p0) {
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public coil.ImageLoader newImageLoader() {
        return null;
    }
}