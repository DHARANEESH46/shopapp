package com.example.shopapp.core.data.repository;

import android.content.Context;
import com.example.shopapp.core.database.dao.ProductDao;
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
public final class ProductRepositoryImpl_Factory implements Factory<ProductRepositoryImpl> {
  private final Provider<ShopApi> shopApiProvider;

  private final Provider<ProductDao> productDaoProvider;

  private final Provider<Context> contextProvider;

  private ProductRepositoryImpl_Factory(Provider<ShopApi> shopApiProvider,
      Provider<ProductDao> productDaoProvider, Provider<Context> contextProvider) {
    this.shopApiProvider = shopApiProvider;
    this.productDaoProvider = productDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ProductRepositoryImpl get() {
    return newInstance(shopApiProvider.get(), productDaoProvider.get(), contextProvider.get());
  }

  public static ProductRepositoryImpl_Factory create(Provider<ShopApi> shopApiProvider,
      Provider<ProductDao> productDaoProvider, Provider<Context> contextProvider) {
    return new ProductRepositoryImpl_Factory(shopApiProvider, productDaoProvider, contextProvider);
  }

  public static ProductRepositoryImpl newInstance(ShopApi shopApi, ProductDao productDao,
      Context context) {
    return new ProductRepositoryImpl(shopApi, productDao, context);
  }
}
