package com.nicole_u_latina_araya_solano.data.database.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nicole_u_latina_araya_solano.model.HouseNicole

@Dao
interface HouseNicoleDao {

    @Insert
    suspend fun insert(houseNicole: HouseNicole)

    @Update
    suspend fun update(houseNicole: HouseNicole)

    @Query("SELECT * FROM housenicole")
    fun getAllItems(): LiveData<List<HouseNicole>>

    @Query("DELETE FROM housenicole")
    suspend fun deleteAllItems()

    @Delete
    suspend fun delete(houseNicole: HouseNicole)

}