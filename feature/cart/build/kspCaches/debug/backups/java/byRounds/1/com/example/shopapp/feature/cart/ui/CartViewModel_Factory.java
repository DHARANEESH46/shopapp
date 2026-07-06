package com.example.shopapp.feature.cart.ui;

import com.example.shopapp.core.domain.repository.AuthRepository;
import com.example.shopapp.core.domain.repository.CartRepository;
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
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  private CartViewModel_Factory(Provider<CartRepository> cartRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(cartRepositoryProvider.get(), authRepositoryProvider.get());
  }

  public static CartViewModel_Factory create(Provider<CartRepository> cartRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new CartViewModel_Factory(cartRepositoryProvider, authRepositoryProvider);
  }

  public static CartViewModel newInstance(CartRepository cartRepository,
      AuthRepository authRepository) {
    return new CartViewModel(cartRepository, authRepository);
  }
}
