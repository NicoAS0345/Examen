package com.nicole_u_latina_araya_solano.data.database.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nicole_u_latina_araya_solano.model.HouseNicole

//Esta es la interfaz para poder acceder a la tabla housenicole
@Dao
interface HouseNicoleDao {

    //Este metodo inserta un objeto a HouseNicole
    @Insert
    suspend fun insert(houseNicole: HouseNicole)

    //Este metodo actualiza un objeto de HouseNicole
    @Update
    suspend fun update(houseNicole: HouseNicole)

    //Aqui se obtienen todos los elementos de la tabla
    @Query("SELECT * FROM housenicole")
    fun getAllItems(): LiveData<List<HouseNicole>>

    //Aqui se eliminan todos los elementos de la tabla
    @Query("DELETE FROM housenicole")
    suspend fun deleteAllItems()

    //Aqui se elimina solo un elemento seleccionado
    @Delete
    suspend fun delete(houseNicole: HouseNicole)

}