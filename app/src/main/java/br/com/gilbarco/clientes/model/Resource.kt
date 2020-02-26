package br.com.gilbarco.clientes.model

class Resource<T>(
    val data: T?,
    val error: String? = null
)