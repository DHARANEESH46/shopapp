package com.example.shopapp.feature.productdetails.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.shopapp.core.designsystem.theme.ButtonLabelText
import com.example.shopapp.core.designsystem.theme.DividerLight
import com.example.shopapp.core.designsystem.theme.Error
import com.example.shopapp.core.designsystem.theme.FilledButtonLabelText
import com.example.shopapp.core.designsystem.theme.OnPrimary
import com.example.shopapp.core.designsystem.theme.OutlinedButtonLabelText
import com.example.shopapp.core.designsystem.theme.Primary
import com.example.shopapp.core.designsystem.theme.PriceLabel
import com.example.shopapp.core.designsystem.theme.ProductTitleText
import com.example.shopapp.core.designsystem.theme.RatingCountText
import com.example.shopapp.core.designsystem.theme.ReviewCountText
import com.example.shopapp.core.designsystem.theme.SortOptionText
import com.example.shopapp.core.designsystem.theme.StarColor
import com.example.shopapp.core.designsystem.theme.Surface
import com.example.shopapp.core.designsystem.theme.SurfaceDark
import com.example.shopapp.core.designsystem.theme.TextPrimary
import com.example.shopapp.core.designsystem.theme.TextSecondary
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.SortOption
import com.example.shopapp.feature.productdetails.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    onProductClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Official Paging 3 Compose API — collectAsLazyPagingItems()
    val lazyPagingItems = viewModel.pagedProducts.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.all_products),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Surface)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceDark)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Button(
                    onClick = { viewModel.showSortSheet() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Icon(
                        imageVector = Icons.Default.SwapVert,
                        contentDescription = stringResource(R.string.sort),
                        tint = OnPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id=R.string.sort),
                        style = ButtonLabelText
                    )
                }
            }
        },
        containerColor = SurfaceDark
    ) { paddingValues ->

        when {
            // First load — full screen spinner
            lazyPagingItems.loadState.refresh is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Primary)
                }
            }

            // First load error
            lazyPagingItems.loadState.refresh is LoadState.Error -> {
                val error = (lazyPagingItems.loadState.refresh as LoadState.Error).error
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = error.message ?: stringResource(R.string.something_went_wrong),
                            color = Error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { lazyPagingItems.retry() },
                            colors = ButtonDefaults.buttonColors(containerColor = Primary)
                        ) {
                            Text(
                                text = stringResource(R.string.retry),
                                style = FilledButtonLabelText
                            )
                        }
                    }
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(SurfaceDark),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Official Paging 3 way to show items
                    items(count = lazyPagingItems.itemCount) { index ->
                        val product = lazyPagingItems[index]
                        if (product != null) {
                            ProductGridCard(
                                product = product,
                                onProductClick = onProductClick
                            )
                        }
                    }

                    // Append loading — spinner at bottom while next page loads
                    if (lazyPagingItems.loadState.append is LoadState.Loading) {
                        item(span = { GridItemSpan(2) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    color = Primary,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                    }

                    // Append error — retry button at bottom
                    if (lazyPagingItems.loadState.append is LoadState.Error) {
                        item(span = { GridItemSpan(2) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    onClick = { lazyPagingItems.retry() },
                                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                                ) {
                                    Text(
                                        text = stringResource(id=R.string.retry),
                                        style = FilledButtonLabelText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Sort bottom sheet — unchanged
        if (uiState.showSortSheet) {
            SortBottomSheet(
                selectedSort = uiState.selectedSort,
                onSortSelected = { viewModel.onSortSelected(it) },
                onDismiss = { viewModel.hideSortSheet() },
                onReset = { viewModel.onSortSelected(SortOption.NAME_A_Z) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    selectedSort: SortOption,
    onSortSelected: (SortOption) -> Unit,
    onDismiss: () -> Unit,
    onReset: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Surface,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        ) {
            val options = listOf(
                SortOption.NAME_A_Z       to "Name (A-Z)",
                SortOption.NAME_Z_A       to "Name (Z-A)",
                SortOption.PRICE_HIGH_LOW to "Price (High-Low)",
                SortOption.PRICE_LOW_HIGH to "Price (Low-High)"
            )

            options.forEach { (option, label) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSortSelected(option) }
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = label,
                        style = SortOptionText.copy(
                            fontWeight = if (selectedSort == option) FontWeight.SemiBold
                            else FontWeight.Normal
                        )
                    )
                    if (selectedSort == option) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = stringResource(R.string.selected),
                            tint = Primary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                if (option != SortOption.PRICE_LOW_HIGH) {
                    HorizontalDivider(color = DividerLight)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onReset,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Primary)
                ) {
                    Text(
                        text = stringResource(R.string.reset),
                        style = OutlinedButtonLabelText
                    )
                }
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text(
                        text = stringResource(R.string.apply),
                        style = FilledButtonLabelText
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductGridCard(
    product: Product,
    onProductClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(285.dp)
            .clickable { onProductClick(product.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Text(
                    text = product.title,
                    style = ProductTitleText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.height(38.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Rp. ${formatPrice(product.price)}",
                    style = PriceLabel
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = StarColor,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "${product.rating}",
                            style = RatingCountText
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${product.reviews.size} Reviews",
                            style = ReviewCountText
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.more),
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

private fun formatPrice(price: Double): String {
    return String.format("%,.0f", price * 15000).replace(',', '.')
}