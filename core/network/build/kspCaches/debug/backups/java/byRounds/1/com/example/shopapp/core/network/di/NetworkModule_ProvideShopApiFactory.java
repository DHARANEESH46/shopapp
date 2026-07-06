package com.example.shopapp.core.network.di;

import com.example.shopapp.core.network.api.ShopApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideShopApiFactory implements Factory<ShopApi> {
  private final Provider<Retrofit> retrofitProvider;

  private NetworkModule_ProvideShopApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ShopApi get() {
    return provideShopApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideShopApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideShopApiFactory(retrofitProvider);
  }

  public static ShopApi provideShopApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideShopApi(retrofit));
  }
}
