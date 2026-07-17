package com.example.shopapp.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.shopapp.core.designsystem.theme.AppTitleText
import com.example.shopapp.core.designsystem.theme.Background
import com.example.shopapp.core.designsystem.theme.BannerCaptionText
import com.example.shopapp.core.designsystem.theme.BannerSubtitleText
import com.example.shopapp.core.designsystem.theme.BannerTitleText
import com.example.shopapp.core.designsystem.theme.CategoryInitialText
import com.example.shopapp.core.designsystem.theme.CategoryLabelText
import com.example.shopapp.core.designsystem.theme.Error
import com.example.shopapp.core.designsystem.theme.HomeProductTitleText
import com.example.shopapp.core.designsystem.theme.LinkText
import com.example.shopapp.core.designsystem.theme.Primary
import com.example.shopapp.core.designsystem.theme.ProductPriceText
import com.example.shopapp.core.designsystem.theme.RatingEmojiText
import com.example.shopapp.core.designsystem.theme.RatingValueText
import com.example.shopapp.core.designsystem.theme.SearchBarPlaceholderText
import com.example.shopapp.core.designsystem.theme.SectionTitleText
import com.example.shopapp.core.designsystem.theme.Surface as SurfaceColor
import com.example.shopapp.core.designsystem.theme.TextPrimary
import com.example.shopapp.core.designsystem.theme.TextSecondary
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProductClick: (Int) -> Unit,
    onSeeAllClick: () -> Unit,
    onCartClick: () -> Unit,
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.mega_mall),
                        style = AppTitleText
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_popup_remainder),
                            contentDescription = stringResource(R.string.notifications),
                            tint = TextPrimary
                        )
                    }
                    BadgedBox(
                        badge = {
                            if (uiState.cartCount > 0) {
                                Badge { Text("${uiState.cartCount}") }
                            }
                        }
                    ) {
                        IconButton(onClick = onCartClick) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = stringResource(R.string.cart),
                                tint = TextPrimary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceColor)
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Search Bar — clicking navigates to SearchScreen
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { onSearchClick() },
                shape = RoundedCornerShape(12.dp),
                color = SurfaceColor,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.search_product_name),
                        style = SearchBarPlaceholderText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            BannerSection()

            Spacer(modifier = Modifier.height(20.dp))

            CategoriesSection()

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.featured_product),
                    style = SectionTitleText
                )
                TextButton(onClick = onSeeAllClick) {
                    Text(
                        text = stringResource(R.string.see_all),
                        style = LinkText
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Primary)
                    }
                }
                uiState.errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = uiState.errorMessage!!, color = Error)
                    }
                }
                else -> {
                    FeaturedProductsGrid(
                        products = uiState.filteredProducts.take(6),
                        onProductClick = onProductClick
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// BannerSection, CategoriesSection, CategoryItem, FeaturedProductsGrid, ProductCard
@Composable
fun BannerSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Primary),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = stringResource(R.string.special_offer), style = BannerSubtitleText)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(R.string.up_to_50_off), style = BannerTitleText)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.on_selected_products), style = BannerCaptionText)
        }
    }
}

@Composable
fun CategoriesSection() {
    val categories = listOf("Foods", "Gift", "Fashion", "Gadget", "Computer")
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.categories), style = SectionTitleText)
        TextButton(onClick = { }) {
            Text(text = stringResource(id = R.string.see_all), style = LinkText)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(categories) { category -> CategoryItem(category = category) }
    }
}

@Composable
fun CategoryItem(category: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(12.dp)).background(SurfaceColor).clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text(text = category.first().toString(), style = CategoryInitialText)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = category, style = CategoryLabelText)
    }
}

@Composable
fun FeaturedProductsGrid(products: List<Product>, onProductClick: (Int) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        products.chunked(2).forEach { rowProducts ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                rowProducts.forEach { product ->
                    ProductCard(product = product, onProductClick = onProductClick, modifier = Modifier.weight(1f))
                }
                if (rowProducts.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onProductClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clickable { onProductClick(product.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier.fillMaxWidth().height(120.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                style = HomeProductTitleText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${product.price}", style = ProductPriceText)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "⭐", style = RatingEmojiText)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${product.rating}", style = RatingValueText)
            }
        }
    }
}