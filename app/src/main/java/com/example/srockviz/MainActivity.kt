package com.example.stockviz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.*
import com.example.srockviz.ui.theme.StockVizTheme
import com.example.stockviz.userinterface.CompanyChartScreen
import com.example.stockviz.userinterface.CompanyListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        com.github.mikephil.charting.utils.Utils.init(this)
        setContent {
            StockVizTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "companyList") {
                        composable("companyList") {
                            CompanyListScreen(navController)
                        }
                        composable("companyChart/{company}") { backStackEntry ->
                            val company = backStackEntry.arguments?.getString("company") ?: ""
                            CompanyChartScreen(company = company, navController = navController)
                        }

                    }
                }
            }
        }
    }
}