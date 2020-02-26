package br.com.gilbarco.clientes.presenter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.gilbarco.clientes.model.CountryRepositiry
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.ui.register_country.RegisterCountryContract

class CountryPresenter (val context: Context): RegisterCountryContract.PresenterImpl {
    private val countryRepositiry = CountryRepositiry(context)

    fun getAll(): LiveData<Resource<List<Country>?>> {
        val liveData = MutableLiveData<Resource<List<Country>?>>()

        countryRepositiry.get(whenSuccess = {
            liveData.value = Resource(it)
        })

        return liveData
    }

    fun save(
        country: Country
    ): LiveData<Resource<Void?>> {
        val liveData = MutableLiveData<Resource<Void?>>()

        countryRepositiry.save(country, whenSuccess = {
            liveData.value = Resource(null)
        })
        return liveData
    }

    fun remove(
        country: Country
    ): LiveData<Resource<Void?>> {
        val liveData = MutableLiveData<Resource<Void?>>()
        countryRepositiry.remove(country, whenSuccess = {
            liveData.value = Resource(null)
        })

        return liveData
    }

    fun update(
        country: Country
    ): LiveData<Resource<Void?>> {
        val liveData = MutableLiveData<Resource<Void?>>()
        countryRepositiry.save(country, whenSuccess = {
            liveData.value = Resource(null)
        })
        return liveData
    }

    fun findForId(
        countryId: Long
    ): LiveData<Resource<Country?>> {
        val liveData = MutableLiveData<Resource<Country?>>()

        countryRepositiry.find(countryId,
            whenSuccess = {
                liveData.value = Resource(it)
            })

        return liveData
    }
}