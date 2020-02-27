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
    ): LiveData<Resource<Void?>> {
        val liveData = MutableLiveData<Resource<Void?>>()
        userRepositiry.remove(user, whenSuccess = {
            liveData.value = Resource(null)
        })

        return liveData
    }

    fun update(
        user: User
    ): LiveData<Resource<Void?>> {
        val liveData = MutableLiveData<Resource<Void?>>()
        userRepositiry.save(user, whenSuccess = {
            liveData.value = Resource(null)
        })
        return liveData
    }

    fun findForId(
        userId: Long
    ): LiveData<Resource<User?>> {
        val liveData = MutableLiveData<Resource<User?>>()

        userRepositiry.find(userId,
            whenSuccess = {
                liveData.value = Resource(it)
            })

        return liveData
    }
}