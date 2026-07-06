package com.example.shopapp.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shopapp.core.designsystem.theme.Primary

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Primary)
    }
}