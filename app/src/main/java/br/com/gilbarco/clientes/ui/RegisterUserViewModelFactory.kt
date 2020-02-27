package br.com.gilbarco.clientes.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.gilbarco.clientes.presenter.UserPresenter

class RegisterUserViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterUserViewModel::class.java)) {
            return RegisterUserViewModel(
                UserPresenter(
                    context
                )
            ) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}
