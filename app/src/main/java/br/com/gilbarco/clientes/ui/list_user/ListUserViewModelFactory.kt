package br.com.gilbarco.clientes.ui.list_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListUserViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}