package com.example.newsapp;

@dagger.hilt.android.HiltAndroidApp
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/example/newsapp/MainApplication;", "Landroid/app/Application;", "Lcoil/ImageLoaderFactory;", "()V", "newImageLoader", "Lcoil/ImageLoader;", "presentation_debug"})
public final class MainApplication extends android.app.Application implements coil.ImageLoaderFactory {
    
    public MainApplication() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public coil.ImageLoader newImageLoader() {
        return null;
    }
}