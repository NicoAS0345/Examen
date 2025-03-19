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

@HiltViewModel
class HouseNicoleViewModel @Inject constructor(
    private val repository: HouseNicoleRepository
): ViewModel()
{
    val allItems: LiveData<List<HouseNicole>> get() = repository.getAllItems().map { list-> list.map{ HouseNicole->HouseNicole.copy(name = HouseNicole.name.replace("+"," "), description = HouseNicole.description.replace("+", " "))} } //esto es para que no se cambien los espacios por más en la interfaz grafica

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