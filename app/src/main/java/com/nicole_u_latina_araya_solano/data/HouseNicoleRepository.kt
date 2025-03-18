package com.nicole_u_latina_araya_solano.data

import javax.inject.Inject
import androidx.lifecycle.LiveData
import com.nicole_u_latina_araya_solano.data.database.interfaces.HouseNicoleDao
import com.nicole_u_latina_araya_solano.model.HouseNicole

class HouseNicoleRepository @Inject constructor(private val houseNicoleDao: HouseNicoleDao){

    fun getAllItems(): LiveData<List<HouseNicole>> {
        return houseNicoleDao.getAllItems()
    }

    suspend fun insert(houseNicole: HouseNicole) {
        houseNicoleDao.insert(houseNicole)
    }

    suspend fun update(houseNicole: HouseNicole) {
        houseNicoleDao.update(houseNicole)
    }

    suspend fun deleteAllItems() {
        houseNicoleDao.deleteAllItems()
    }
    suspend fun delete(houseNicole: HouseNicole) {
        houseNicoleDao.delete(houseNicole)
    }
}