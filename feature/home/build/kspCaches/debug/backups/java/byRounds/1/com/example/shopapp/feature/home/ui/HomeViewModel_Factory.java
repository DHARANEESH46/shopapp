package com.example.shopapp.feature.home.ui;

import com.example.shopapp.core.domain.repository.AuthRepository;
import com.example.shopapp.core.domain.repository.CartRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetProductsUseCase> getProductsUseCaseProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  private HomeViewModel_Factory(Provider<GetProductsUseCase> getProductsUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.getProductsUseCaseProvider = getProductsUseCaseProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getProductsUseCaseProvider.get(), cartRepositoryProvider.get(), authRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetProductsUseCase> getProductsUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new HomeViewModel_Factory(getProductsUseCaseProvider, cartRepositoryProvider, authRepositoryProvider);
  }

  public static HomeViewModel newInstance(GetProductsUseCase getProductsUseCase,
      CartRepository cartRepository, AuthRepository authRepository) {
    return new HomeViewModel(getProductsUseCase, cartRepository, authRepository);
  }
}
