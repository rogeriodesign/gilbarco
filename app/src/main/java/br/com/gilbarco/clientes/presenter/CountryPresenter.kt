package br.com.gilbarco.clientes.presenter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.gilbarco.clientes.model.CountryRepositiry
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.ui.RegisterCountryContract

class CountryPresenter (val context: Context): RegisterCountryContract.PresenterImpl {

    private lateinit var viewMain: RegisterCountryContract.ViewImpl
    private val countryRepositiry = CountryRepositiry(context)

    override fun setView(view: RegisterCountryContract.ViewImpl) {
        viewMain = view
    }

    override fun getAll() {
        countryRepositiry.get(whenSuccess = {
            viewMain.setList(Resource(it))
        })
    }

    override fun save(
        country: Country
    ) {
        countryRepositiry.save(country, whenSuccess = {
            viewMain.responseSave(Resource(null))
        })
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