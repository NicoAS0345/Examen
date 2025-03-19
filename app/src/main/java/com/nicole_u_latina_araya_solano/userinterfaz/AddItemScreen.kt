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
    //Se establecen los atributos que se van a capturar
    var itemName by remember { mutableStateOf("") } // Estado para el nombre del ítem
    var description by remember { mutableStateOf("") }
    var squiare_meters by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo de texto para el nombre del ítem
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("Nombre de la casa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Campo para la descripcion
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripcion de la casa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Campo para los metros cuadrados
        OutlinedTextField(
            value = squiare_meters,
            onValueChange = { squiare_meters = it },
            label = { Text("Metros cuadrados") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar el ítem
        Button(
            onClick = {
                if (itemName.isNotBlank()) {
                    val square_meters_double = squiare_meters.toDoubleOrNull() ?: 0.0 // Convertir a Double

                    // Crear una nueva casa y lo agrega a la base de datos
                    val nuevaHouse = HouseNicole(
                        name = itemName,
                        description = description,
                        square_meters = square_meters_double,
                    )
                    viewModel.insert(nuevaHouse)
                    navController.popBackStack() // Regresar a la pantalla anterior
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar ítem")
        }
    }
}

