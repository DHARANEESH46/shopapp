package com.example.shopapp.feature.productdetails.ui;

import com.example.shopapp.core.domain.usecase.GetProductsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ProductDetailsViewModel_Factory implements Factory<ProductDetailsViewModel> {
  private final Provider<GetProductsUseCase> getProductsUseCaseProvider;

  private ProductDetailsViewModel_Factory(Provider<GetProductsUseCase> getProductsUseCaseProvider) {
    this.getProductsUseCaseProvider = getProductsUseCaseProvider;
  }

  @Override
  public ProductDetailsViewModel get() {
    return newInstance(getProductsUseCaseProvider.get());
  }

  public static ProductDetailsViewModel_Factory create(
      Provider<GetProductsUseCase> getProductsUseCaseProvider) {
    return new ProductDetailsViewModel_Factory(getProductsUseCaseProvider);
  }

  public static ProductDetailsViewModel newInstance(GetProductsUseCase getProductsUseCase) {
    return new ProductDetailsViewModel(getProductsUseCase);
  }
}
