package br.com.gilbarco.clientes.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.gilbarco.clientes.model.asynctask.BaseAsyncTask
import br.com.gilbarco.clientes.model.database.AppDatabase
import br.com.gilbarco.clientes.model.database.dao.UserDAO
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.model.model.User
import br.com.gilbarco.clientes.model.model.UserCountry

class UserRepository(private val context: Context){
    private val dao = AppDatabase.getInstance(context).userDAO

    fun save(
        user: User,
        whenSuccess: () -> Unit
    ) {
        BaseAsyncTask(whenExecuted = {
            dao.save(user)
        }, whenEnded = {
            whenSuccess()
        }).execute()
    }

    fun remove(
        user: User,
        whenSuccess: () -> Unit
    ) {
        BaseAsyncTask(whenExecuted = {
            dao.remove(user)
        }, whenEnded = {
            whenSuccess()
        }).execute()
    }

    fun get(
        whenSuccess: (List<User>) -> Unit
    ) {
        var list: List<User> = listOf()
        BaseAsyncTask(whenExecuted = {
            list = dao.getAll()
        }, whenEnded = {
            whenSuccess(list)
        }).execute()
    }

    fun getWithCountry(
        whenSuccess: (List<User>) -> Unit
    ) {
        var list: MutableList<User> = mutableListOf()
        BaseAsyncTask(whenExecuted = {
            val l = dao.getAllCountry()
            for (i in l) {
                list.add(i.bind())
            }
        }, whenEnded = {
            whenSuccess(list as List<User>)
        }).execute()
    }

    fun find(
        id: Long,
        whenSuccess: (User?) -> Unit
    ) {
        var user: User? = null
        BaseAsyncTask(whenExecuted = {
            val userCountry: UserCountry? = dao.findForId(id)
            userCountry?.let {
                user = it.bind()
            }
        }, whenEnded = {
            whenSuccess(user)
        }).execute()
    }
}
