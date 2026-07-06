package com.example.shopapp.feature.individualproduct.ui;

import androidx.lifecycle.SavedStateHandle;
import com.example.shopapp.core.domain.repository.AuthRepository;
import com.example.shopapp.core.domain.repository.CartRepository;
import com.example.shopapp.core.domain.usecase.GetProductByIdUseCase;
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
public final class IndividualProductViewModel_Factory implements Factory<IndividualProductViewModel> {
  private final Provider<GetProductByIdUseCase> getProductByIdUseCaseProvider;

  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private IndividualProductViewModel_Factory(
      Provider<GetProductByIdUseCase> getProductByIdUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.getProductByIdUseCaseProvider = getProductByIdUseCaseProvider;
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public IndividualProductViewModel get() {
    return newInstance(getProductByIdUseCaseProvider.get(), cartRepositoryProvider.get(), authRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static IndividualProductViewModel_Factory create(
      Provider<GetProductByIdUseCase> getProductByIdUseCaseProvider,
      Provider<CartRepository> cartRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new IndividualProductViewModel_Factory(getProductByIdUseCaseProvider, cartRepositoryProvider, authRepositoryProvider, savedStateHandleProvider);
  }

  public static IndividualProductViewModel newInstance(GetProductByIdUseCase getProductByIdUseCase,
      CartRepository cartRepository, AuthRepository authRepository,
      SavedStateHandle savedStateHandle) {
    return new IndividualProductViewModel(getProductByIdUseCase, cartRepository, authRepository, savedStateHandle);
  }
}
