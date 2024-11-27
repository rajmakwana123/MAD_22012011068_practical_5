package com.example.mad_22012011068_practical_5


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(context = context, navController = navController)
        }
        composable("registration") {
            RegisterScreen(context = context, navController = navController)
        }
    }
}