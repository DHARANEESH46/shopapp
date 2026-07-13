package com.example.shopapp.feature.resetpassword.ui.updatepassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopapp.core.designsystem.component.ShopButton
import com.example.shopapp.core.designsystem.component.ShopTextField
import com.example.shopapp.core.designsystem.component.ShopTopAppBar
import com.example.shopapp.core.designsystem.theme.TextSecondary
import com.example.shopapp.feature.resetpassword.R

@Composable
fun UpdatePasswordScreen(
    onBackClick: () -> Unit,
    onSaveUpdateClick: () -> Unit,
    viewModel: UpdatePasswordViewModel = hiltViewModel()
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
                text = stringResource(R.string.update_password),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.update_password_subtitle),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(40.dp))

            ShopTextField(
                value = uiState.newPassword,
                onValueChange = viewModel::onNewPasswordChange,
                label = stringResource(R.string.new_password),
                placeholder = stringResource(R.string.enter_new_password),
                visualTransformation = if (uiState.newPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = viewModel::toggleNewPasswordVisibility) {
                        Icon(
                            imageVector = if (uiState.newPasswordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = if (uiState.newPasswordVisible)
                                stringResource(R.string.hide_password)
                            else
                                stringResource(R.string.show_password),
                            tint = TextSecondary
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.height(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.password_hint),
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            ShopTextField(
                value = uiState.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                label = stringResource(R.string.confirm_new_password),
                placeholder = stringResource(R.string.re_enter_new_password),
                visualTransformation = if (uiState.confirmPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = viewModel::toggleConfirmPasswordVisibility) {
                        Icon(
                            imageVector = if (uiState.confirmPasswordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = if (uiState.confirmPasswordVisible)
                                stringResource(R.string.hide_password)
                            else
                                stringResource(R.string.show_password),
                            tint = TextSecondary
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            ShopButton(
                text = stringResource(R.string.save_update),
                onClick = onSaveUpdateClick
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}