package com.example.shopapp.core.database.di;

import com.example.shopapp.core.database.ShopDatabase;
import com.example.shopapp.core.database.dao.CartDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DatabaseModule_ProvideCartDaoFactory implements Factory<CartDao> {
  private final Provider<ShopDatabase> databaseProvider;

  private DatabaseModule_ProvideCartDaoFactory(Provider<ShopDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CartDao get() {
    return provideCartDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCartDaoFactory create(
      Provider<ShopDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCartDaoFactory(databaseProvider);
  }

  public static CartDao provideCartDao(ShopDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCartDao(database));
  }
}
