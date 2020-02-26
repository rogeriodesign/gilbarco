package br.com.gilbarco.clientes.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    val code: Long = 0,
    val name: String = "",
    val cnpj: String = "",
    val countryId: Long = 0
)
