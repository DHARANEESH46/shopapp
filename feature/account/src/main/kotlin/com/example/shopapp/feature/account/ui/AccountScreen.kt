package com.example.shopapp.feature.account.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.shopapp.core.designsystem.component.ShopButton
import com.example.shopapp.core.designsystem.theme.Background
import com.example.shopapp.core.designsystem.theme.Divider
import com.example.shopapp.core.designsystem.theme.Primary
import com.example.shopapp.core.designsystem.theme.TextPrimary
import com.example.shopapp.core.designsystem.theme.TextSecondary
import com.example.shopapp.core.domain.model.User
import com.example.shopapp.feature.account.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    onLogout: () -> Unit,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.my_account),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Profile Image
            AsyncImage(
                model = user?.image,
                contentDescription = stringResource(R.string.profile_picture),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Full Name
            Text(
                text = "${user?.firstName} ${user?.lastName}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Username
            Text(
                text = "@${user?.username}",
                fontSize = 14.sp,
                color = Primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Info Card
            user?.let { UserInfoCard(user = it) }

            Spacer(modifier = Modifier.weight(1f))

            // Logout Button
            ShopButton(
                text = stringResource(R.string.logout),
                onClick = {
                    viewModel.logout()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun UserInfoCard(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoRow(label = stringResource(R.string.email), value = user.email)
            HorizontalDivider(color = Divider, modifier = Modifier.padding(vertical = 12.dp))
            HorizontalDivider(color = Divider, modifier = Modifier.padding(vertical = 12.dp))
            InfoRow(label = stringResource(R.string.username), value = user.username)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary
        )
    }
}