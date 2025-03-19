package com.nicole_u_latina_araya_solano.viewmodel

import androidx.lifecycle.ViewModel
import com.nicole_u_latina_araya_solano.data.HouseNicoleRepository
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.nicole_u_latina_araya_solano.model.HouseNicole
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Esta va a ser la clase que permite que el viewmodel se comunique con el repositorio y la informacion se inyecte a la capa de datos
@HiltViewModel
class HouseNicoleViewModel @Inject constructor(
    private val repository: HouseNicoleRepository
): ViewModel()
{
    //Este LiveData es el que contiene todos los elementos que se encuentran en HouseNicole
    val allItems: LiveData<List<HouseNicole>> get() = repository.getAllItems()

    //Estos metodos son iguales a los que se han explicado en el DAO y el repositorio pero con la diferencia de que se ejecutan desde el viewmodel
    fun insert(houseNicole: HouseNicole) = viewModelScope.launch {
        repository.insert(houseNicole)
    }

    fun update(houseNicole: HouseNicole) = viewModelScope.launch {
        repository.update(houseNicole)
    }
    fun deleteItem(houseNicole: HouseNicole) {
        viewModelScope.launch {
            repository.delete(houseNicole)
        }
    }

    fun deleteAllItems() = viewModelScope.launch {
        repository.deleteAllItems()
    }
}