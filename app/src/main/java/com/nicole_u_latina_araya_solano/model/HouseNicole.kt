package com.nicole_u_latina_araya_solano.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "housenicole")
data class HouseNicole (

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val square_meters: Double = 0.0,
    val Solano: String = "",
    val isSelected: Boolean=false

    ):Serializable
