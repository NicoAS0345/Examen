package com.heiner.myapplication.userinterfaz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nicole_u_latina_araya_solano.model.HouseNicole
import com.nicole_u_latina_araya_solano.data.HouseNicoleRepository
import com.nicole_u_latina_araya_solano.viewmodel.HouseNicoleViewModel


@Composable
fun AddItemScreen(
    navController: NavController,
    viewModel: HouseNicoleViewModel = hiltViewModel()
) {
    var itemName by remember { mutableStateOf("") } // Estado para el nombre del ítem

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo de texto para el nombre del ítem
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Nombre del ítem") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar el ítem
        Button(
            onClick = {
                if (itemName.isNotBlank()) {
                    // Crear un nuevo ítem y agregarlo a la base de datos
                    val newItem = HouseNicole(name = itemName)
                    viewModel.insert(newItem)
                    navController.popBackStack() // Regresar a la pantalla anterior
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar ítem")
        }
    }
}

