package br.com.gilbarco.clientes.presenter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.UserRepository
import br.com.gilbarco.clientes.model.model.User
import br.com.gilbarco.clientes.ui.RegisterUserContract

class UserPresenter(val context: Context) : RegisterUserContract.PresenterImpl {
    private lateinit var viewMain: RegisterUserContract.ViewImpl
    private val userRepositiry = UserRepository(context)

    override fun setView(view: RegisterUserContract.ViewImpl) {
        viewMain = view
    }

    fun getAll() {
        userRepositiry.get(whenSuccess = {
            viewMain.setList(Resource(it))
        })
    }

    override fun getAllWithCountry() {
        userRepositiry.getWithCountry(whenSuccess = {
            viewMain.setList(Resource(it))
        })
    }

    override fun save(
        user: User
    ) {
        userRepositiry.save(user, whenSuccess = {
            viewMain.responseSave(Resource(null))
        })
    }

    fun remove(
        user: User
    ) {
        userRepositiry.remove(user, whenSuccess = {
        })
    }

    fun update(
        user: User
    ) {
        userRepositiry.save(user, whenSuccess = {
        })
    }

    fun findForId(
        userId: Long
    ) {
        userRepositiry.find(userId,
            whenSuccess = {
            })
    }
}