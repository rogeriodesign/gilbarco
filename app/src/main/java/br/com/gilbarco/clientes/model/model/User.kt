package br.com.gilbarco.clientes.model.model

import androidx.room.*


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Long? = 0,
    @ColumnInfo(name = "user_code")
    val code: Long = 0,
    @ColumnInfo(name = "user_name")
    val name: String = "",
    @ColumnInfo(name = "user_cnpj")
    val cnpj: String = "",
    @ColumnInfo(name = "user_country_id")
    val countryId: Long = 0,
    var country: String? = null
)

/*@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId")),
    ForeignKey(entity = Country::class, parentColumns = arrayOf("id"), childColumns = arrayOf("countryId"))])
data class UserCountry (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val userId: Long? = null,
    val countryId: Long? = null
)

class DataUserCountry(
    @Embedded
    val user: User,
    @Embedded
    val country: Country
)*/

data class UserCountry(
    @Embedded
    val user: User,
    @Relation(entity = Country::class, parentColumn = "user_country_id", entityColumn = "id")
    val country: Country?
) {
    fun bind(): User {
        user.country = country?.name
        return user
    }
}
