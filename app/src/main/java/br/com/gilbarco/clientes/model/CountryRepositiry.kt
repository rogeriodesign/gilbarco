package br.com.gilbarco.clientes.model

import android.content.Context
import br.com.gilbarco.clientes.model.asynctask.BaseAsyncTask
import br.com.gilbarco.clientes.model.database.AppDatabase
import br.com.gilbarco.clientes.model.model.Country

class CountryRepositiry(
    private val context: Context
) {
    private val dao = AppDatabase.getInstance(context).countryDAO


    fun save(
        country: Country,
        whenSuccess: () -> Unit
    ) {
        BaseAsyncTask(whenExecuted = {
            dao.save(country)
        }, whenEnded = {
            whenSuccess()
        }).execute()
    }

    fun remove(
        country: Country,
        whenSuccess: () -> Unit
    ) {
        BaseAsyncTask(whenExecuted = {
            dao.remove(country)
        }, whenEnded = {
            whenSuccess()
        }).execute()
    }

    fun get(
        whenSuccess: (List<Country>) -> Unit
    ) {
        var list: List<Country> = listOf()
        BaseAsyncTask(whenExecuted = {
            list = dao.getAll()
        }, whenEnded = {
            whenSuccess(list)
        }).execute()
    }

    fun find(
        id: Long,
        whenSuccess: (Country?) -> Unit
    ) {
        var country: Country? = null
        BaseAsyncTask(whenExecuted = {
            country = dao.findForId(id)
        }, whenEnded = {
            whenSuccess(country)
        }).execute()
    }
}