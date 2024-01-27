package com.example.newsapp;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/newsapp/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "connectivityObserver", "Lcom/example/newsapp/presentation/connectivity/ConnectivityObserverImpl;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "ViewModelFactoryProvider", "presentation_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    private com.example.newsapp.presentation.connectivity.ConnectivityObserverImpl connectivityObserver;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lcom/example/newsapp/MainActivity$ViewModelFactoryProvider;", "", "detailViewModelFactory", "Lcom/example/newsapp/presentation/features/details/DetailsViewModel$Factory;", "presentation_debug"})
    @dagger.hilt.EntryPoint
    @dagger.hilt.InstallIn(value = {dagger.hilt.android.components.ActivityComponent.class})
    public static abstract interface ViewModelFactoryProvider {
        
        @org.jetbrains.annotations.NotNull
        public abstract com.example.newsapp.presentation.features.details.DetailsViewModel.Factory detailViewModelFactory();
    }
}