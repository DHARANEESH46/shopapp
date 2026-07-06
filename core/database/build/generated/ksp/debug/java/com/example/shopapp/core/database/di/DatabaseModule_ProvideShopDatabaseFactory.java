package com.example.shopapp.core.database.di;

import android.content.Context;
import com.example.shopapp.core.database.ShopDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideShopDatabaseFactory implements Factory<ShopDatabase> {
  private final Provider<Context> contextProvider;

  private DatabaseModule_ProvideShopDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ShopDatabase get() {
    return provideShopDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideShopDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideShopDatabaseFactory(contextProvider);
  }

  public static ShopDatabase provideShopDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideShopDatabase(context));
  }
}
