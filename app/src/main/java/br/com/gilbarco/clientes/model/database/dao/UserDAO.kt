package br.com.gilbarco.clientes.model.database.dao

import androidx.room.*
//import br.com.gilbarco.clientes.model.model.DataUserCountry
import br.com.gilbarco.clientes.model.model.User
import br.com.gilbarco.clientes.model.model.UserCountry

@Dao
interface UserDAO {
    @Query("SELECT * FROM users ORDER BY user_id DESC")
    fun getAll(): List<User>

    @Query("SELECT * FROM users ORDER BY user_id DESC")
    fun getAllCountry(): List<UserCountry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

    @Delete
    fun remove(user: User)

    /*@Query("SELECT * FROM UserCountry " +
            "INNER JOIN User ON User.id = UserCountry.userId " +
            "INNER JOIN Country ON Country.id = UserCountry.countryId " +
            "WHERE UserCountry.userId = :id")
    fun findForId(id: Long): DataUserCountry?*/

    @Query("SELECT * FROM users " +
            "WHERE user_id = :id")
    fun findForId(id: Long): UserCountry?
}
