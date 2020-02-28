package br.com.gilbarco.clientes.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.presenter.CountryPresenter

class CountryViewModel(private val countryPresenter: CountryPresenter): ViewModel(),
    CountryContract.ViewImpl {

    val country = MutableLiveData<Country>()
    val countrySelected = MutableLiveData<Country>()

    private val _saveResult = MutableLiveData<Resource<String>>()
    val saveResult: LiveData<Resource<String>> = _saveResult
    private val _countriesResult = MutableLiveData<Resource<List<Country>?>>()
    val countriesResult: LiveData<Resource<List<Country>?>> = _countriesResult

    override fun save(country: Country){
        countryPresenter.save(country)
    }
    override fun responseSave(resource: Resource<Void?>) {
        if (resource.error == null) {
            country.value = null
            _saveResult.value = Resource(data= "País ${country.value?.name} gravado com sucesso")
        } else {
            _saveResult.value = Resource(data= null, error = "Não foi possivél gravar o país ${country.value?.name}.\n ${resource.error}")
        }
    }

    override fun getCountries() {
        countryPresenter.getAll()
    }

    override fun setList(resource: Resource<List<Country>?>) {
        if (resource.error == null) {
            _countriesResult.value = resource
        } else {
            _countriesResult.value = Resource(data= null, error = "Não foi possivel carregar os países.\n${resource.error}")
        }
    }

    fun init(){
        countryPresenter.setView(this)
    }

    fun getCountry(): LiveData<Country> {
        return country
    }

    fun resetSaveResult(){
        _saveResult.value = null
    }

    fun getCountrySelected(): LiveData<Country> {
        return countrySelected
    }

    fun resetCountrySelected(){
        countrySelected.value = null
    }

    fun setName(text : String){
        if(country.value != null) {
            country.value!!.name = text
        } else {
            country.value = Country(name = text)
        }
    }

    fun setCode(text : String){
        Log.i("campo code",text)
        if(text!=null && text.isNotEmpty()) {
            if (country.value != null) {
                Log.i("campo code convert",text.toLong().toString())
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