package br.com.gilbarco.clientes.ui.list_country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListCountryViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}