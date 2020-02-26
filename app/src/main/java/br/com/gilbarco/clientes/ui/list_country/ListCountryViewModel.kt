package br.com.gilbarco.clientes.ui.list_country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gilbarco.clientes.model.model.Country

class ListCountryViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countrySelected = MutableLiveData<Country>()

    fun getCountries(): LiveData<List<Country>> {
        return countries
    }

    fun getCountrySelected(): LiveData<Country> {
        return countrySelected
    }
}