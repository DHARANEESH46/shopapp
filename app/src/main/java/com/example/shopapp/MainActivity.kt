package com.example.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.core.designsystem.theme.ShopAppTheme
import com.example.shopapp.core.domain.repository.AuthRepository
import com.example.shopapp.navigation.Routes
import com.example.shopapp.navigation.ShopNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val startDestination = if (authRepository.isLoggedIn()) {
            Routes.HOME
        } else {
            Routes.LOGIN
        }

        setContent {
            ShopAppTheme {
                val navController = rememberNavController()
                ShopNavGraph(
                    navController = navController,
                    startDestination = startDestination
                )
            }
        }
    }
}