package br.com.gilbarco.clientes.ui

import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.User

interface UserContract {
    interface ViewImpl {
        fun responseSave(resource: Resource<Void?>)
        fun save(user: User)
        fun getUsers()
        fun setList(resource: Resource<List<User>?>)
    }

    interface PresenterImpl {
        fun setView(view: ViewImpl)
        fun save(user: User)
        fun getAllWithCountry()
    }
}