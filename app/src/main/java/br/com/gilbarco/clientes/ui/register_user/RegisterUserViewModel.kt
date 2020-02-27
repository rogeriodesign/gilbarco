package br.com.gilbarco.clientes.ui.register_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.model.model.User

class RegisterUserViewModel: ViewModel() {

    val user = MutableLiveData<User>()
    val countries = MutableLiveData<List<Country>>()
    val countrySelected = MutableLiveData<Country>()

    fun getUser(): LiveData<User> {
        return user
    }

    fun getCountries(): LiveData<List<Country>> {
        return countries
    }

    fun getCountrySelected(): LiveData<Country> {
        return countrySelected
    }

    fun setName(text : String){
        if(user.value != null) {
            user.value!!.name = text
        } else {
            user.value = User(name = text)
        }
    }

    fun setCode(text : String){
        if(text!=null && text.isNotEmpty()) {
            if (user.value != null) {
                user.value!!.code = text.toLong()
            } else {
                user.value = User(code = text.toLong())
            }
        }
    }

    fun setCnpj(text : String){
        if(user.value != null) {
            user.value!!.cnpj = text
        } else {
            user.value = User(cnpj = text)
        }
    }
}