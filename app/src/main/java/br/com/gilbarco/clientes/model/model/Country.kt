package br.com.gilbarco.clientes.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    val code: Long? = 0,
    val name: String? = "",
    val description: String? = ""
)
