package com.example.newsapp.presentation.features.details;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DetailsViewModel_Factory_Impl implements DetailsViewModel.Factory {
  private final DetailsViewModel_Factory delegateFactory;

  DetailsViewModel_Factory_Impl(DetailsViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public DetailsViewModel create(String articleUrl) {
    return delegateFactory.get(articleUrl);
  }

  public static Provider<DetailsViewModel.Factory> create(
      DetailsViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new DetailsViewModel_Factory_Impl(delegateFactory));
  }
}
