package br.com.gilbarco.clientes.presenter

import android.content.Context
import android.util.Log
import br.com.gilbarco.clientes.model.CountryRepositiry
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.ui.CountryContract

class CountryPresenter (val context: Context): CountryContract.PresenterImpl {

    private lateinit var viewMain: CountryContract.ViewImpl
    private val countryRepositiry = CountryRepositiry(context)

    override fun setView(view: CountryContract.ViewImpl) {
        viewMain = view
    }

    override fun getAll() {
        countryRepositiry.get(whenSuccess = {
            Log.i("pega os paises",it.toString())
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