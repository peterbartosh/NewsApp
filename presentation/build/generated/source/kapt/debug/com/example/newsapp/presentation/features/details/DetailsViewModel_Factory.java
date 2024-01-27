package com.example.newsapp.presentation.features.details;

import com.example.domain.GetArticleByUrlUseCase;
import dagger.internal.DaggerGenerated;
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
public final class DetailsViewModel_Factory {
  private final Provider<GetArticleByUrlUseCase> getArticleByUrlUseCaseProvider;

  public DetailsViewModel_Factory(Provider<GetArticleByUrlUseCase> getArticleByUrlUseCaseProvider) {
    this.getArticleByUrlUseCaseProvider = getArticleByUrlUseCaseProvider;
  }

  public DetailsViewModel get(String articleUrl) {
    return newInstance(getArticleByUrlUseCaseProvider.get(), articleUrl);
  }

  public static DetailsViewModel_Factory create(
      Provider<GetArticleByUrlUseCase> getArticleByUrlUseCaseProvider) {
    return new DetailsViewModel_Factory(getArticleByUrlUseCaseProvider);
  }

  public static DetailsViewModel newInstance(GetArticleByUrlUseCase getArticleByUrlUseCase,
      String articleUrl) {
    return new DetailsViewModel(getArticleByUrlUseCase, articleUrl);
  }
}
