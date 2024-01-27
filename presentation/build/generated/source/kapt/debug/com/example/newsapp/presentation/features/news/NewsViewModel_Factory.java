package com.example.newsapp.presentation.features.news;

import com.example.domain.GetLatestNewsFlowUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NewsViewModel_Factory implements Factory<NewsViewModel> {
  private final Provider<GetLatestNewsFlowUseCase> getLatestNewsFlowUseCaseProvider;

  public NewsViewModel_Factory(
      Provider<GetLatestNewsFlowUseCase> getLatestNewsFlowUseCaseProvider) {
    this.getLatestNewsFlowUseCaseProvider = getLatestNewsFlowUseCaseProvider;
  }

  @Override
  public NewsViewModel get() {
    return newInstance(getLatestNewsFlowUseCaseProvider.get());
  }

  public static NewsViewModel_Factory create(
      Provider<GetLatestNewsFlowUseCase> getLatestNewsFlowUseCaseProvider) {
    return new NewsViewModel_Factory(getLatestNewsFlowUseCaseProvider);
  }

  public static NewsViewModel newInstance(GetLatestNewsFlowUseCase getLatestNewsFlowUseCase) {
    return new NewsViewModel(getLatestNewsFlowUseCase);
  }
}
