package br.com.gilbarco.clientes.model.database.dao

import androidx.room.*
import br.com.gilbarco.clientes.model.model.User

@Dao
interface UserDAO {
    @Query("SELECT User.*, Country.id AS countryId, Country.code AS countryCode, Country.name AS countryName, Country.description AS countryDescription FROM User " +
                "INNER JOIN Country ON Country.id = User.countryId " +
                "ORDER BY id DESC")
    fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

    @Delete
    fun remove(user: User)

    @Query("SELECT User.*, Country.id AS countryId, Country.code AS countryCode, Country.name AS countryName, Country.description AS countryDescription FROM User " +
            "INNER JOIN Country ON Country.id = User.countryId " +
            "WHERE Country.id = :id")
    fun findForId(id: Long): User?
}
