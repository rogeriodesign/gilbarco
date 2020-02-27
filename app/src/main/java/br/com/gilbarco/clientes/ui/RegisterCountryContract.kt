package br.com.gilbarco.clientes.ui

import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.Country

interface RegisterCountryContract {
    interface ViewImpl {
        fun save(user: Country)
        fun responseSave(resource: Resource<Void?>)
        fun getCountries()
        fun setList(resource: Resource<List<Country>?>)
    }

    interface PresenterImpl {
        fun setView(view: ViewImpl)
        fun save(user: Country)
        fun getAll()
    }
}
