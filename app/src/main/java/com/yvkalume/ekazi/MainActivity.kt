package com.yvkalume.ekazi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yvkalume.ekazi.databinding.ActivityMainBinding
import com.yvkalume.ekazi.screens.HomeScreen
import com.yvkalume.ekazi.screens.LoginScreen

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable(route = "login") {
                        LoginScreen(navController = navController)
                    }
                    composable(route = "home") {
                        HomeScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
