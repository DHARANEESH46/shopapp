package com.example.shopapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.shopapp.core.designsystem.theme.Primary
import com.example.shopapp.feature.account.ui.AccountScreen
import com.example.shopapp.feature.account.ui.AccountViewModel
import com.example.shopapp.feature.home.ui.HomeScreen
import com.example.shopapp.feature.individualproduct.ui.IndividualProductScreen
import com.example.shopapp.feature.login.ui.LoginScreen
import com.example.shopapp.feature.productdetails.ui.ProductDetailsScreen
import com.example.shopapp.feature.cart.ui.CartScreen
import com.example.shopapp.feature.wishlist.ui.WishlistScreen

object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val WISHLIST = "wishlist"
    const val ORDER = "order"
    const val ACCOUNT = "account"
    const val PRODUCT_DETAILS = "product_details"
    const val INDIVIDUAL_PRODUCT = "individual_product/{productId}"
    const val CART = "cart"

    fun individualProduct(productId: Int) = "individual_product/$productId"
}

val bottomNavRoutes = listOf(Routes.HOME, Routes.WISHLIST, Routes.ORDER, Routes.ACCOUNT)

@Composable
fun ShopNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val accountViewModel: AccountViewModel = hiltViewModel()
    val accountUiState by accountViewModel.uiState.collectAsState()
    val userImageUrl = accountUiState.user?.image ?: ""

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavRoutes) {
                ShopBottomNavBar(
                    currentRoute = currentRoute,
                    userImageUrl = userImageUrl,
                    onHomeClick = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    onWishlistClick = {
                        navController.navigate(Routes.WISHLIST) { launchSingleTop = true }
                    },
                    onOrderClick = {
                        navController.navigate(Routes.ORDER) { launchSingleTop = true }
                    },
                    onAccountClick = {
                        navController.navigate(Routes.ACCOUNT) { launchSingleTop = true }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            composable(route = Routes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }

            composable(route = Routes.HOME) {
                HomeScreen(
                    onProductClick = { productId ->
                        navController.navigate(Routes.individualProduct(productId))
                    },
                    onSeeAllClick = {
                        navController.navigate(Routes.PRODUCT_DETAILS)
                    },
                    onCartClick = {
                        navController.navigate(Routes.CART)
                    }
                )
            }

            composable(route = Routes.WISHLIST) {
                WishlistScreen(
                    onProductClick = { productId ->
                        navController.navigate(Routes.individualProduct(productId))
                    }
                )
            }
            composable(route = Routes.ORDER) {
                PlaceholderScreen(title = "Orders")
            }

            composable(route = Routes.ACCOUNT) {
                AccountScreen(
                    onLogout = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    viewModel = accountViewModel
                )
            }

            composable(route = Routes.PRODUCT_DETAILS) {
                ProductDetailsScreen(
                    onProductClick = { productId ->
                        navController.navigate(Routes.individualProduct(productId))
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(
                route = Routes.INDIVIDUAL_PRODUCT,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) {
                IndividualProductScreen(
                    onBackClick = { navController.popBackStack() },
                    onCartClick = { navController.navigate(Routes.CART) }
                )
            }

            composable(route = Routes.CART) {
                CartScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun ShopBottomNavBar(
    currentRoute: String?,
    userImageUrl: String,
    onHomeClick: () -> Unit,
    onWishlistClick: () -> Unit,
    onOrderClick: () -> Unit,
    onAccountClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == Routes.HOME,
            onClick = onHomeClick,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = currentRoute == Routes.WISHLIST,
            onClick = onWishlistClick,
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Wishlist") },
            label = { Text("Wishlist") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = currentRoute == Routes.ORDER,
            onClick = onOrderClick,
            icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "Order") },
            label = { Text("Order") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = currentRoute == Routes.ACCOUNT,
            onClick = onAccountClick,
            icon = {
                if (userImageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = userImageUrl,
                        contentDescription = "Account",
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Account"
                    )
                }
            },
            label = { Text("Account") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Primary,
                selectedTextColor = Primary,
                indicatorColor = Color.White,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}

@Composable
fun PlaceholderScreen(title: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = "$title - Coming Soon!", color = Color.Gray)
    }
}