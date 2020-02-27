package br.com.gilbarco.clientes.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.gilbarco.clientes.model.LoginDataSource
import br.com.gilbarco.clientes.model.LoginRepository
import br.com.gilbarco.clientes.presenter.LoginPresenter

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginPresenter = LoginPresenter()
            ) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}
