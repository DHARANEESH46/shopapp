package com.example.shopapp.core.data.repository;

import android.content.Context;
import com.example.shopapp.core.database.dao.CartDao;
import com.example.shopapp.core.network.api.ShopApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<ShopApi> shopApiProvider;

  private final Provider<CartDao> cartDaoProvider;

  private final Provider<Context> contextProvider;

  private AuthRepositoryImpl_Factory(Provider<ShopApi> shopApiProvider,
      Provider<CartDao> cartDaoProvider, Provider<Context> contextProvider) {
    this.shopApiProvider = shopApiProvider;
    this.cartDaoProvider = cartDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(shopApiProvider.get(), cartDaoProvider.get(), contextProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<ShopApi> shopApiProvider,
      Provider<CartDao> cartDaoProvider, Provider<Context> contextProvider) {
    return new AuthRepositoryImpl_Factory(shopApiProvider, cartDaoProvider, contextProvider);
  }

  public static AuthRepositoryImpl newInstance(ShopApi shopApi, CartDao cartDao, Context context) {
    return new AuthRepositoryImpl(shopApi, cartDao, context);
  }
}
