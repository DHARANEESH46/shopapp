package com.example.shopapp.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val ShopTypography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        color = TextPrimary
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        color = TextPrimary
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        color = TextPrimary
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        color = TextPrimary
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = TextPrimary
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        color = TextPrimary
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = TextPrimary
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = TextSecondary
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = TextSecondary
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = TextPrimary
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = TextPrimary
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 9.sp,
        lineHeight = 12.sp,
        color = TextPrimary
    )
)

val RatingText = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    color = TextPrimary
)

val CommentText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 20.sp,
    color = TextComment
)

// Buttons
val FilledButtonLabelText = TextStyle(
    fontWeight = FontWeight.SemiBold,
    color = OnPrimary
)
val OutlinedButtonLabelText = TextStyle(
    fontWeight = FontWeight.SemiBold,
    color = Primary
)
val ButtonLabelText = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 15.sp,
    color = OnPrimary
)

// Sort sheet
val SortOptionText = TextStyle(
    fontSize = 15.sp,
    color = TextPrimary
)

// Product grid card
val ProductTitleText = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp,
    color = TextPrimary
)
val RatingCountText = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    color = TextPrimary
)
val ReviewCountText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 11.sp,
    color = TextSecondary
)

val PriceLabel = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 13.sp,
    color = Secondary
)
val PriceCalc = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = TextSecondary
)
val PriceSubtotal = TextStyle(               // cart item line subtotal, wishlist item price
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    color = Secondary
)
val PriceTotal = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    color = TextPrimary
)

// Account screen
val ProfileNameText = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 22.sp,
    color = TextPrimary
)
val UsernameText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = Primary
)

// Cart screen (also reused by wishlist)
val LabelCaption = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    color = TextSecondary
)
val ItemCountText = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
    color = Primary
)
val EmptyStateText = TextStyle(              // "Your wishlist is empty"
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = TextSecondary
)
val CardTitleText = TextStyle(               // cart item title, wishlist item title
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    color = TextPrimary
)
val QuantityValueText = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 13.sp,
    color = Primary
)
val PasswordHintText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = TextSecondary
)

val FieldLabelText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = TextPrimary
)

val OtpDigitText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    color = TextPrimary
)

val TimerText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    color = TextSecondary
)

// ── Login + Search screen tokens ──
val ErrorMessageText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    color = Error
)

val SearchFieldText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    color = TextPrimary
)
val SearchFieldPlaceholderText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    color = TextSecondary
)
val SectionHeaderText = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp,
    color = TextSecondary
)
val SearchResultItemText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = TextPrimary
)
val EmptyStateEmojiText = TextStyle(
    fontSize = 64.sp
)
val EmptyStateTitleText = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    color = TextPrimary
)
val EmptyStateSubtitleText = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    color = TextSecondary,
    textAlign = TextAlign.Center
)

// ── NEW: Home screen tokens ──
val AppTitleText = TextStyle(              // "Mega Mall" top bar title
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    color = Primary
)
val SearchBarPlaceholderText = TextStyle(  // "Search product name" home search bar
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = TextHint
)
val SectionTitleText = TextStyle(          // "Featured Product" / "Categories" headers
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    color = TextPrimary
)
val LinkText = TextStyle(                  // "See All" links
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = Primary
)
val BannerTitleText = TextStyle(           // "Up to 50% Off" banner headline
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    color = OnPrimary
)
val BannerSubtitleText = TextStyle(        // "Special Offer" banner eyebrow
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = OnPrimary
)
val BannerCaptionText = TextStyle(         // "On selected products" banner caption
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = OnPrimaryMuted
)
val CategoryInitialText = TextStyle(       // category chip initial letter
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    color = Primary
)
val CategoryLabelText = TextStyle(         // category chip label
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = TextSecondary
)
val HomeProductTitleText = TextStyle(      // featured product grid card title
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
    color = TextPrimary
)
val ProductPriceText = TextStyle(          // featured product grid card price
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    color = Secondary
)
val RatingEmojiText = TextStyle(
    fontSize = 12.sp
)
val RatingValueText = TextStyle(           // numeric rating value next to star
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = TextSecondary
)