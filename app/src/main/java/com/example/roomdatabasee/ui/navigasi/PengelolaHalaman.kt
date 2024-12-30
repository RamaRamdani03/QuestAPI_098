package com.example.roomdatabasee.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdatabasee.ui.view.DestinasiDetail
import com.example.roomdatabasee.ui.view.DestinasiEntry
import com.example.roomdatabasee.ui.view.DestinasiHome
import com.example.roomdatabasee.ui.view.DestinasiUpdate
import com.example.roomdatabasee.ui.view.DetailScreen
import com.example.roomdatabasee.ui.view.EntryMhsScreen
import com.example.roomdatabasee.ui.view.HomeScreen
import com.example.roomdatabasee.ui.view.UpdateScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = {
                    navController.navigate("${DestinasiDetail.route}/$it")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailScreen(
                    navigateToItemUpdate = {
                        navController.navigate("${DestinasiUpdate.route}/$nim")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiUpdate.NIM)
            nim?.let { nim ->
                UpdateScreen(
                    onBack = {navController.popBackStack()},
                    onNavigate = {navController.popBackStack()}
                )
            }
        }
    }
}