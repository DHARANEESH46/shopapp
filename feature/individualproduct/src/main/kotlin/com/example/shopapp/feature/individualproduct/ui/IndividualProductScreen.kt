package com.example.shopapp.feature.individualproduct.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.shopapp.core.designsystem.theme.CommentText
import com.example.shopapp.core.designsystem.theme.Divider
import com.example.shopapp.core.designsystem.theme.DividerLight
import com.example.shopapp.core.designsystem.theme.Error
import com.example.shopapp.core.designsystem.theme.OnPrimary
import com.example.shopapp.core.designsystem.theme.OnSecondary
import com.example.shopapp.core.designsystem.theme.Primary
import com.example.shopapp.core.designsystem.theme.RatingText
import com.example.shopapp.core.designsystem.theme.Secondary
import com.example.shopapp.core.designsystem.theme.StarColor
import com.example.shopapp.core.designsystem.theme.StarEmpty
import com.example.shopapp.core.designsystem.theme.StepperBackground
import com.example.shopapp.core.designsystem.theme.Surface
import com.example.shopapp.core.designsystem.theme.SurfaceVariant
import com.example.shopapp.core.designsystem.theme.TextBody
import com.example.shopapp.core.designsystem.theme.TextPrimary
import com.example.shopapp.core.designsystem.theme.TextSecondary
import com.example.shopapp.core.domain.model.Product
import com.example.shopapp.core.domain.model.Review
import com.example.shopapp.feature.individualproduct.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndividualProductScreen(
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    viewModel: IndividualProductViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showCartDialog by remember { mutableStateOf(false) }

    // Auto-dismiss snackbar after 2 seconds
    LaunchedEffect(uiState.snackbarMessage) {
        if (uiState.snackbarMessage != null) {
            delay(2000)
            viewModel.clearSnackbar()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.detail_product),
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
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = stringResource(R.string.share),
                            tint = TextPrimary
                        )
                    }
                    Box {
                        IconButton(onClick = onCartClick) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = stringResource(R.string.cart),
                                tint = TextPrimary
                            )
                        }
                        if (uiState.cartCount > 0) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(Primary, CircleShape)
                                    .align(Alignment.TopEnd),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${uiState.cartCount}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = OnPrimary
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Surface)
            )
        },
        containerColor = Surface,
        bottomBar = {
            val product = uiState.product
            if (product != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Surface)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { viewModel.toggleWishlist(product) },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (uiState.isInWishlist) Secondary else Surface
                        ),
                        border = BorderStroke(1.dp, Secondary)
                    ) {
                        Icon(
                            imageVector = if (uiState.isInWishlist) Icons.Filled.Favorite
                            else Icons.Filled.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.wishlist),
                            tint = if (uiState.isInWishlist) OnSecondary else Secondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (uiState.isInWishlist) "Added" else "Wishlist",
                            style = MaterialTheme.typography.labelLarge,
                            color = if (uiState.isInWishlist) OnSecondary else Secondary
                        )
                    }

                    Button(
                        onClick = { showCartDialog = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = null,
                            tint = OnPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = stringResource(R.string.add_to_cart),
                            style = MaterialTheme.typography.labelLarge,
                            color = OnPrimary
                        )
                    }
                }

                if (showCartDialog) {
                    AddToCartDialog(
                        productName = product.title,
                        productPrice = product.price,
                        onDismiss = { showCartDialog = false },
                        onConfirm = { quantity ->
                            viewModel.addToCart(product, quantity)
                            showCartDialog = false
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Primary)
                    }
                }

                uiState.errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = uiState.errorMessage ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Error
                        )
                    }
                }

                uiState.product != null -> {
                    val product = uiState.product
                    if (product != null) {
                        IndividualProductContent(
                            product = product,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedVisibility(
                    visible = uiState.snackbarMessage != null,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight },
                        animationSpec = tween(400)
                    ) + fadeIn(animationSpec = tween(400)),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> fullHeight },
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .background(
                                color = Color(0xFF323232),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = uiState.snackbarMessage ?: "",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddToCartDialog(
    productName: String,
    productPrice: Double,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var quantity by remember { mutableStateOf(1) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.add_to_cart),
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close),
                        tint = TextSecondary
                    )
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.quantity),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(StepperBackground, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = { if (quantity > 1) quantity-- },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Text(
                                    text = "−",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        }
                        Text(
                            text = "$quantity",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Primary, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = { quantity++ },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Text(
                                    text = "+",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = OnPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.total),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rp ${formatPrice(productPrice * quantity)}",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onConfirm(quantity) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text(
                        text = stringResource(id = R.string.add_to_cart),
                        style = MaterialTheme.typography.labelLarge,
                        color = OnPrimary
                    )
                }
            }
        },
        confirmButton = {}
    )
}

@Composable
private fun IndividualProductContent(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(SurfaceVariant),
            contentScale = ContentScale.Fit
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Rp ${formatPrice(product.price)}",
                style = MaterialTheme.typography.titleLarge,
                color = Secondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = StarColor,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${product.rating}", style = RatingText)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${product.reviews.size} Reviews",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = DividerLight)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.description_product),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = DividerLight)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Review (${product.reviews.size})",
                    style = MaterialTheme.typography.titleMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = StarColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${product.rating}", style = RatingText)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (product.reviews.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_reviews_yet),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                product.reviews.forEach { review ->
                    ReviewItem(review = review)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ReviewItem(review: Review) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Divider),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = review.reviewerName.first().toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = TextBody
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = review.reviewerName,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = formatDate(review.date),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            repeat(5) { index ->
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = if (index < review.rating) StarColor else StarEmpty,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = review.comment,
            style = CommentText
        )
    }
}

private fun formatDate(isoDate: String): String {
    return try {
        val inputFormat = java.text.SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            java.util.Locale.getDefault()
        )
        val outputFormat = java.text.SimpleDateFormat(
            "dd MMM yyyy",
            java.util.Locale.getDefault()
        )
        val date = inputFormat.parse(isoDate)
        outputFormat.format(date ?: return isoDate)
    } catch (e: Exception) {
        isoDate
    }
}

private fun formatPrice(price: Double): String {
    return String.format("%,.0f", price * 15000).replace(',', '.')
}