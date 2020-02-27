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
    ) {
        countryRepositiry.remove(country, whenSuccess = {
        })
    }

    fun update(
        country: Country
    ) {
        countryRepositiry.save(country, whenSuccess = {
        })
    }

    fun findForId(
        countryId: Long
    ) {
        countryRepositiry.find(countryId,
            whenSuccess = {
            })
    }
}