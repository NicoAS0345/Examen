package com.heiner.myapplication.userinterfaz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nicole_u_latina_araya_solano.ui.theme.HouseNicoleTheme
import com.nicole_u_latina_araya_solano.viewmodel.HouseNicoleViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nicole_u_latina_araya_solano.model.HouseNicole
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.abs
import androidx.compose.ui.text.TextStyle


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemListScreen(
    navController: NavController = rememberNavController(), // NavController para navegar
    viewModel: HouseNicoleViewModel = hiltViewModel()
) {
    // Observamos el LiveData usando observeAsState
    val items by viewModel.allItems.observeAsState(emptyList<HouseNicole>())

    var itemToDelete by remember { mutableStateOf<HouseNicole?>(null) }
    var cancelSwipeAction by remember { mutableStateOf<(() -> Unit)?>(null) }
    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            isRefreshing = false
        }
    )

    if (itemToDelete != null) {
        AlertDialog(
            onDismissRequest = { itemToDelete = null }, // Cierra el diálogo
            title = { Text(text = "Confirmar borrado") },
            text = { Text(text = "¿Estás seguro de que quieres borrar este elemento?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        itemToDelete?.let { item ->
                            viewModel.deleteItem(item) // Borra el elemento
                            itemToDelete = null // Cierra el diálogo
                        }
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        itemToDelete = null
                        cancelSwipeAction?.invoke()
                    }
                // Cierra el diálogo sin borrar
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            // Botón flotante con ícono de "agregar"
            FloatingActionButton(
                onClick = {
                    // Navegar al fragmento de agregar ítem
                    navController.navigate("addItem")
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // Ícono de "agregar"
                    contentDescription = "Agregar ítem"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Mostramos el número de cartas seleccionadas
            Text(
                text = "Cartas seleccionadas: ${items.count { it.isSelected }}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            // Botón para eliminar todos los elementos
            Button(
                onClick = { viewModel.deleteAllItems() },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Eliminar todos los elementos")
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .pullRefresh(pullRefreshState)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(items) { item ->
                        ItemCard(
                            item = item,
                            onClick = { viewModel.update(item.copy(isSelected = !item.isSelected)) },
                            onSwipe = {
                                itemToDelete = item
                                cancelSwipeAction = null
                            },
                            onEditClick = {
                                val json = Gson().toJson(item)
                                val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
                                navController.navigate("editItem/$encodedJson")
                            },
                            onCancelSwipe = { cancelSwipeAction = it }
                        )
                    }
                }
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }


        }
    }
}

@Composable
fun ItemCard(
    item: HouseNicole,
    onClick: () -> Unit,
    onSwipe: () -> Unit,
    onEditClick: () -> Unit,
    onCancelSwipe: (() -> Unit) -> Unit
) {
    val offsetX = remember { Animatable(0f) } // Controla la animación del desplazamiento
    val scope = rememberCoroutineScope()



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .offset { IntOffset(offsetX.value.toInt(), 0) } // Desplaza la tarjeta en X
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        // Si el desplazamiento es suficiente, eliminar el item
                        if (abs(offsetX.value) > 300f) {
                            scope.launch {
                                offsetX.animateTo(
                                    targetValue = if (offsetX.value > 0) 1000f else -1000f, // Se desliza fuera de la pantalla
                                    animationSpec = tween(durationMillis = 300)
                                )
                                onSwipe() // Llama a la acción de eliminar
                            }
                        } else {
                            // Si el desplazamiento es pequeño, regresar a la posición original
                            scope.launch {
                                offsetX.animateTo(0f, animationSpec = tween(300))
                            }
                        }
                    }
                ) { _, dragAmount ->
                    scope.launch {
                        offsetX.snapTo(offsetX.value + dragAmount) // Mueve la tarjeta con el gesto
                    }
                }
            }
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isSelected) Color.Red else Color.White
        ),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.name,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${item.square_meters}",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                modifier = Modifier
                    .clickable { onEditClick() }
                    .padding(start = 8.dp)
            )

        }
    }
    LaunchedEffect(key1 = item) {
        onCancelSwipe {
            scope.launch { offsetX.animateTo(0f, tween(300)) }
        }
    }
    onCancelSwipe {
        scope.launch { offsetX.animateTo(0f, tween(300)) }
    }
}


@Preview(showBackground = true)
@Composable
fun ItemListScreenPreview() {
    HouseNicoleTheme {
        ItemListScreen() // Previsualización de la pantalla
    }
}