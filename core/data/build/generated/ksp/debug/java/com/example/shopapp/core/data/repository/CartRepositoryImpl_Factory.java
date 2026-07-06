package com.example.shopapp.core.data.repository;

import com.example.shopapp.core.database.dao.CartDao;
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
public final class CartRepositoryImpl_Factory implements Factory<CartRepositoryImpl> {
  private final Provider<CartDao> cartDaoProvider;

  private CartRepositoryImpl_Factory(Provider<CartDao> cartDaoProvider) {
    this.cartDaoProvider = cartDaoProvider;
  }

  @Override
  public CartRepositoryImpl get() {
    return newInstance(cartDaoProvider.get());
  }

  public static CartRepositoryImpl_Factory create(Provider<CartDao> cartDaoProvider) {
    return new CartRepositoryImpl_Factory(cartDaoProvider);
  }

  public static CartRepositoryImpl newInstance(CartDao cartDao) {
    return new CartRepositoryImpl(cartDao);
  }
}
