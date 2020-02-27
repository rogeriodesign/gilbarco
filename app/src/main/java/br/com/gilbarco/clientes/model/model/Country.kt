package br.com.gilbarco.clientes.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0L,
    var code: Long? = 0L,
    var name: String? = "",
    var description: String? = ""
)
