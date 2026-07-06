package com.example.shopapp.feature.account.ui;

import com.example.shopapp.core.domain.repository.AuthRepository;
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
public final class AccountViewModel_Factory implements Factory<AccountViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private AccountViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public AccountViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static AccountViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new AccountViewModel_Factory(authRepositoryProvider);
  }

  public static AccountViewModel newInstance(AuthRepository authRepository) {
    return new AccountViewModel(authRepository);
  }
}
