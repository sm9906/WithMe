package com.bonobono.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.login.JoinScreen
import com.bonobono.presentation.ui.login.LoginScreen
import com.bonobono.presentation.ui.theme.AndroidTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
//                    MainScreen()
//                    LoginScreen(rememberNavController())
                    JoinScreen(navController = rememberNavController())
                }
            }
        }
    }
}
