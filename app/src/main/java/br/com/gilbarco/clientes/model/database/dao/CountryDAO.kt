package br.com.gilbarco.clientes.model.database.dao

import androidx.room.*
import br.com.gilbarco.clientes.model.model.Country

@Dao
interface CountryDAO {
    @Query("SELECT * FROM countries ORDER BY id DESC")
    fun getAll(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(country: Country)

    @Delete
    fun remove(country: Country)

    @Query("SELECT * FROM countries WHERE id = :id")
    fun findForId(id: Long): Country?
}