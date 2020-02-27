package br.com.gilbarco.clientes.presenter

import br.com.gilbarco.clientes.model.LoginRepository
import br.com.gilbarco.clientes.model.Result
import br.com.gilbarco.clientes.model.model.LoggedInUser
import br.com.gilbarco.clientes.ui.login.LoginContract

class LoginPresenter: LoginContract.PresenterImpl {
    private val loginRepository = LoginRepository()

    override fun login(username: String, password: String): Result<LoggedInUser> {
        return loginRepository.login(username, password)
    }

    fun logout() {
        loginRepository.logout()
    }

    fun isLoggedIn(): Boolean {
        return loginRepository.isLoggedIn
    }
}