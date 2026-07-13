package com.example.shopapp.feature.resetpassword.ui.resetpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopapp.core.designsystem.component.ShopButton
import com.example.shopapp.core.designsystem.component.ShopTextField
import com.example.shopapp.core.designsystem.component.ShopTopAppBar
import com.example.shopapp.feature.resetpassword.R

@Composable
fun ResetPasswordScreen(
    onBackClick: () -> Unit,
    onResetClick: (String) -> Unit,
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            ShopTopAppBar(title = "", onBackClick = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.reset_password),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.reset_password_subtitle),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(40.dp))

            ShopTextField(
                value = uiState.emailOrPhone,
                onValueChange = viewModel::onEmailOrPhoneChange,
                label = stringResource(R.string.email_phone),
                placeholder = stringResource(R.string.enter_email_or_phone)
            )

            Spacer(modifier = Modifier.height(40.dp))

            ShopButton(
                text = stringResource(R.string.reset),
                onClick = { onResetClick(uiState.emailOrPhone) }
            )
        }
    }
}