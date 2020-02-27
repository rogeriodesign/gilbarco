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

    fun setName(text : String){
        if(country.value != null) {
            country.value!!.name = text
        } else {
            country.value = Country(name = text)
        }
    }

    fun setCode(text : String){
        if(text!=null && text.isNotEmpty()) {
            if (country.value != null) {
                country.value!!.code = text.toLong()
            } else {
                country.value = Country(code = text.toLong())
            }
        }
    }

    fun setDescription(text : String){
        if(country.value != null) {
            country.value!!.description = text
        } else {
            country.value = Country(description = text)
        }
    }
}