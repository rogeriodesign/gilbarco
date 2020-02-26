package br.com.gilbarco.clientes.ui.register_country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.Country

class RegisterCountryViewModel: ViewModel() {

    val country = MutableLiveData<Country>()

    fun getCountry(): LiveData<Country> {
        return country
    }
}