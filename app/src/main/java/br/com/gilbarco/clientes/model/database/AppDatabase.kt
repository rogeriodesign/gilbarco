package br.com.gilbarco.clientes.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.gilbarco.clientes.model.database.converter.ConverterCountry
import br.com.gilbarco.clientes.model.database.dao.CountryDAO
import br.com.gilbarco.clientes.model.database.dao.UserDAO
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.model.model.User

private const val NAME_DATA_BASE = "clientsgilbarco.db"

@Database(entities = [User::class, Country::class], version = 1, exportSchema = false)
@TypeConverters(ConverterCountry::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDAO: UserDAO
    abstract val countryDAO: CountryDAO

    companion object {

        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {

            if(::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NAME_DATA_BASE
            ).build()

            return db
        }
    }
}