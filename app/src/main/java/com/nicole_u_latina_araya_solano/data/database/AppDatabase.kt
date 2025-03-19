package com.nicole_u_latina_araya_solano.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.nicole_u_latina_araya_solano.data.database.interfaces.HouseNicoleDao
import com.nicole_u_latina_araya_solano.model.HouseNicole

//En esta clase es en donde se define la base de datos de la aplicación, se indica la entidad y su version
@Database(entities = [HouseNicole::class], version = 1, exportSchema = false)
abstract class AppDatabase() : RoomDatabase ()
{

    //Aqui es donde se accede a DAO
    abstract fun HouseNicoleDao(): HouseNicoleDao

    //Este es el companion object para implementar el patron singleton
    companion object {
        //Esta decoracion permite que la variable INSTANCE siempre sea visible para otros hilos
        @Volatile
        private var INSTANCE: AppDatabase?=null

        //Este metodo es el que obtiene una instancia unica de la base de datos (esto es el singleton)
        fun getDatabase(context: Context): AppDatabase{
            //Si el INSTANCE no es nulo entonces se retorns
            return INSTANCE ?: synchronized(this){
                //Si es nulo entonces se crea una nueva instancia de la base de datos
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "housenicole_database" //Este es el nombre de la base de datos
                ).build()
                INSTANCE = instance//Aqui ya se obtuvo la instancia y se asigna a INSTANCE
                instance   //Retorna la instancia creada
            }
        }

    }
}

