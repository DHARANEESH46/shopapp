package com.example.shopapp.feature.resetpassword.ui.verification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopapp.core.designsystem.component.ShopButton
import com.example.shopapp.core.designsystem.component.ShopTopAppBar
import com.example.shopapp.core.designsystem.theme.Divider
import com.example.shopapp.core.designsystem.theme.FieldLabelText
import com.example.shopapp.core.designsystem.theme.OtpDigitText
import com.example.shopapp.core.designsystem.theme.Primary
import com.example.shopapp.core.designsystem.theme.TimerText
import com.example.shopapp.feature.resetpassword.R

@Composable
fun VerificationScreen(
    contact: String,
    onBackClick: () -> Unit,
    onChangeClick: () -> Unit,
    onContinueClick: () -> Unit,
    viewModel: VerificationViewModel = hiltViewModel()
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
                text = stringResource(R.string.verification),
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = "${stringResource(R.string.verification_subtitle)} $contact ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = stringResource(R.string.change),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Primary,
                    modifier = Modifier.clickable(onClick = onChangeClick)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(R.string.verification_code),
                style = FieldLabelText
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                uiState.codeDigits.forEachIndexed { index, digit ->
                    OutlinedTextField(
                        value = digit,
                        onValueChange = { viewModel.onDigitChange(index, it) },
                        modifier = Modifier.size(width = 56.dp, height = 56.dp),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        textStyle = OtpDigitText.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = Divider
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val minutes = uiState.secondsRemaining / 60
            val seconds = uiState.secondsRemaining % 60
            val timeText = "%02d:%02d".format(minutes, seconds)

            if (uiState.secondsRemaining > 0) {
                Text(
                    text = "${stringResource(R.string.resend_code_in)} $timeText",
                    style = TimerText
                )
            } else {
                TextButton(onClick = viewModel::onResendClick) {
                    Text(text = stringResource(R.string.resend_code), color = Primary)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            ShopButton(
                text = stringResource(R.string.continue_text),
                onClick = onContinueClick
            )
        }
    }
}