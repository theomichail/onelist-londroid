package com.capitalone.mobile.onelist.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.capitalone.mobile.onelist.core.ui.ObserveAsEvents
import com.capitalone.mobile.onelist.core.ui.PreviewTheme
import com.capitalone.mobile.onelist.core.ui.components.oneListColoredText
import com.capitalone.mobile.onelist.feature.login.LoginViewModel.UiState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    ObserveAsEvents(viewModel.loginSuccessful) {
        navigateToHome()
    }

    LoginScreen(
        uiState = uiState.value,
        onLogin = viewModel::login
    )
}

@Composable
private fun LoginScreen(
    uiState: UiState,
    onLogin: (String, String) -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isSystemInDarkTheme()

    DisposableEffect(systemUiController) {
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
        )
        onDispose {
            systemUiController.setNavigationBarColor(
                color = Color.Transparent,
                darkIcons = !useDarkIcons,
            )
        }
    }

    Box(Modifier.fillMaxSize()) {
        LoginLayout(
            uiState = uiState,
            onLogin = onLogin
        )
        if (uiState.isLoading) {
            LoadingOverlay()
        }
    }
}

@Composable
private fun LoginLayout(
    uiState: UiState,
    onLogin: (String, String) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = oneListColoredText,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
        )

        LoginInputBox(
            uiState = uiState,
            onLogin = onLogin
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColumnScope.LoginInputBox(
    uiState: UiState,
    onLogin: (String, String) -> Unit,
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier.weight(1f),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(8.dp)
                )
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Login", style = MaterialTheme.typography.titleLarge)

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Username") },
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
            )

            if (uiState.incorrectCredentials) {
                Text(
                    text = "Incorrect credentials, please try again!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(onClick = { onLogin(username, password) }) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
private fun LoadingOverlay() {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Logging in...", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.size(16.dp))
        LinearProgressIndicator()
    }
}

@Preview(widthDp = 350, heightDp = 700)
@Composable
private fun LoginPreview() {
    PreviewTheme {
        LoginScreen(
            uiState = UiState(),
            onLogin = {_,_ ->}
        )
    }
}

@Preview(widthDp = 350, heightDp = 700)
@Composable
private fun LoginErrorPreview() {
    PreviewTheme {
        LoginScreen(
            uiState = UiState(incorrectCredentials = true),
            onLogin = {_,_ ->}
        )
    }
}

@Preview(widthDp = 350, heightDp = 700)
@Composable
private fun LoginLoadingPreview() {
    PreviewTheme {
        LoginScreen(
            uiState = UiState(isLoading = true),
            onLogin = {_,_ ->}
        )
    }
}