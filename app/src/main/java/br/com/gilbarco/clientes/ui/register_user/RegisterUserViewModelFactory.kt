package br.com.gilbarco.clientes.ui.register_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegisterUserViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}