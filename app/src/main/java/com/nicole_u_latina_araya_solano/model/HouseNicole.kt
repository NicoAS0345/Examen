package com.nicole_u_latina_araya_solano.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//Aqui se crea la entidad y se le asigna un nombre de tabla
@Entity(tableName = "housenicole")

//Esta clase es la que define la entidad
data class HouseNicole (

    //Estos son los atributos solicitados que tuviera la entidad, se establece que el id sea la primarykey y que se auto genere
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val square_meters: Double = 0.0,
    val Solano: String = "",
    val isSelected: Boolean=false

    ):Serializable
