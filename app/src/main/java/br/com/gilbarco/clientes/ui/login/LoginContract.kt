package br.com.gilbarco.clientes.ui.login

import br.com.gilbarco.clientes.model.Result
import br.com.gilbarco.clientes.model.model.LoggedInUser

interface LoginContract {
    interface ViewImpl {
        fun login(username: String, password: String)
    }

    interface PresenterImpl {
        fun login(username: String, password: String): Result<LoggedInUser>
    }
}
