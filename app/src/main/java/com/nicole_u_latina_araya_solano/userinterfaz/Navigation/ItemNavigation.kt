package com.heiner.myapplication.userinterfaz.Navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nicole_u_latina_araya_solano.model.HouseNicole
import com.heiner.myapplication.userinterfaz.AddItemScreen
import com.heiner.myapplication.userinterfaz.EditItemScreen
import com.heiner.myapplication.userinterfaz.ItemListScreen
import com.nicole_u_latina_araya_solano.viewmodel.HouseNicoleViewModel

@Composable
fun Navigation(viewModelStoreOwner: ViewModelStoreOwner = LocalViewModelStoreOwner.current!!) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "itemList"
    ) {
        // Pantalla principal
        composable("itemList") {
            val viewModel: HouseNicoleViewModel = hiltViewModel(viewModelStoreOwner)
            ItemListScreen(navController = navController, viewModel)
        }

        // Pantalla para agregar ítem
        composable("addItem") {
            val viewModel: HouseNicoleViewModel = hiltViewModel(viewModelStoreOwner)
            AddItemScreen(navController = navController, viewModel)
        }
        // Pantalla para editar ítem
        composable(
            route = "editItem/{itemJson}",
            arguments = listOf(
                navArgument("itemJson") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            // Recuperamos el objeto Item
            val json = backStackEntry.arguments?.getString("itemJson")
            val item = Gson().fromJson(json, HouseNicole::class.java)

            val viewModel: HouseNicoleViewModel = hiltViewModel(viewModelStoreOwner)
            if (item != null) {
                EditItemScreen(
                    item = item, // Pasamos el objeto Item
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}
