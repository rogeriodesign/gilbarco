package br.com.gilbarco.clientes.ui.register_country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegisterCountryViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}