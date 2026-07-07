package com.example.shopapp.core.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shopapp.core.domain.model.AuthState
import com.example.shopapp.core.domain.model.User
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.core.network.api.ShopApi
import com.example.shopapp.core.network.dto.LoginRequestDto
import com.example.shopapp.core.network.mapper.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class AuthRepositoryImpl @Inject constructor(
    private val shopApi: ShopApi,
    @ApplicationContext private val context: Context
) : AuthRepository {

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USER_ID = intPreferencesKey("user_id")
        val USERNAME = stringPreferencesKey("username")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_FIRST_NAME = stringPreferencesKey("user_first_name")
        val USER_LAST_NAME = stringPreferencesKey("user_last_name")
        val USER_IMAGE = stringPreferencesKey("user_image")
        val USER_PHONE = stringPreferencesKey("user_phone")
    }

    override fun login(username: String, password: String): Flow<AuthState> = flow {
        emit(AuthState.Loading)
        try {
            val response = shopApi.login(
                LoginRequestDto(username = username, password = password)
            )
            val user = response.toDomain()
            context.dataStore.edit { prefs ->
                prefs[IS_LOGGED_IN] = true
                prefs[USER_ID] = user.id
                prefs[USERNAME] = user.username
                prefs[USER_EMAIL] = user.email
                prefs[USER_FIRST_NAME] = user.firstName
                prefs[USER_LAST_NAME] = user.lastName
                prefs[USER_IMAGE] = user.image
            }
            emit(AuthState.Success(user))
        } catch (e: Exception) {
            emit(AuthState.Error(e.message ?: "Login failed"))
        }
    }

    override suspend fun logout() {

        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = false
            prefs[USER_ID] = 0
            prefs[USERNAME] = ""
            prefs[USER_EMAIL] = ""
            prefs[USER_FIRST_NAME] = ""
            prefs[USER_LAST_NAME] = ""
            prefs[USER_IMAGE] = ""
        }
    }

    override fun isLoggedIn(): Boolean {
        return runBlocking {
            context.dataStore.data.first()[IS_LOGGED_IN] ?: false
        }
    }

    override fun getCurrentUser(): User? {
        return runBlocking {
            val prefs = context.dataStore.data.first()
            val isLoggedIn = prefs[IS_LOGGED_IN] ?: false
            if (!isLoggedIn) return@runBlocking null
            User(
                id = prefs[USER_ID] ?: 0,
                username = prefs[USERNAME] ?: "",
                email = prefs[USER_EMAIL] ?: "",
                firstName = prefs[USER_FIRST_NAME] ?: "",
                lastName = prefs[USER_LAST_NAME] ?: "",
                image = prefs[USER_IMAGE] ?: "",
            )
        }
    }

    override fun getCurrentUserFlow(): Flow<User?> {
        return context.dataStore.data.map { prefs ->
            val isLoggedIn = prefs[IS_LOGGED_IN] ?: false
            if (!isLoggedIn) null
            else User(
                id = prefs[USER_ID] ?: 0,
                username = prefs[USERNAME] ?: "",
                email = prefs[USER_EMAIL] ?: "",
                firstName = prefs[USER_FIRST_NAME] ?: "",
                lastName = prefs[USER_LAST_NAME] ?: "",
                image = prefs[USER_IMAGE] ?: "",
            )
        }
    }
}