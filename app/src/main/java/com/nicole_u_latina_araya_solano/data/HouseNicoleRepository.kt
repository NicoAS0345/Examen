package com.nicole_u_latina_araya_solano.data

import javax.inject.Inject
import androidx.lifecycle.LiveData
import com.nicole_u_latina_araya_solano.data.database.interfaces.HouseNicoleDao
import com.nicole_u_latina_araya_solano.model.HouseNicole

//Este es el repositorio en donde se manejan las operacones de datos relacionadas a la base de datos y la entidad
class HouseNicoleRepository @Inject constructor(private val houseNicoleDao: HouseNicoleDao){

    //Aqui se obtienen todos los elementos de la base de datos
    fun getAllItems(): LiveData<List<HouseNicole>> {
        return houseNicoleDao.getAllItems()
    }

    //Aqui se inserta un objeto a la base de datos
    suspend fun insert(houseNicole: HouseNicole) {
        houseNicoleDao.insert(houseNicole)
    }

    //Aqui se actualiza un objeto que ya exista en la base de datos
    suspend fun update(houseNicole: HouseNicole) {
        houseNicoleDao.update(houseNicole)
    }

    //Este elimina todos los elementos de la base de datos
    suspend fun deleteAllItems() {
        houseNicoleDao.deleteAllItems()
    }

    //Y aqui se elimina un objeto especifico de la base de datos
    suspend fun delete(houseNicole: HouseNicole) {
        houseNicoleDao.delete(houseNicole)
    }
}