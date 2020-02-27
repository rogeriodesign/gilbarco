package br.com.gilbarco.clientes.model.model

import androidx.room.*


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long? = 0L,
    @ColumnInfo(name = "user_code")
    var code: Long = 0L,
    @ColumnInfo(name = "user_name")
    var name: String = "",
    @ColumnInfo(name = "user_cnpj")
    var cnpj: String = "",
    @ColumnInfo(name = "user_country_id")
    var countryId: Long? = 0L,
    @Ignore
    var country: Country? = null
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
        user.country = country
        return user
    }
}
