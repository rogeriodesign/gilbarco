package br.com.gilbarco.clientes.presenter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.UserRepository
import br.com.gilbarco.clientes.model.model.User
import br.com.gilbarco.clientes.ui.register_user.RegisterUserContract

class UserPresenter (context: Context): RegisterUserContract.PresenterImpl {
    private val userRepositiry = UserRepository(context)

    fun getAll(): LiveData<Resource<List<User>?>> {
        val liveData = MutableLiveData<Resource<List<User>?>>()

        userRepositiry.get(whenSuccess = {
            liveData.value = Resource(it)
        })

        return liveData
    }

    fun save(
        user: User
    ): LiveData<Resource<Void?>> {
        val liveData = MutableLiveData<Resource<Void?>>()

        userRepositiry.save(user, whenSuccess = {
            liveData.value = Resource(null)
        })
        return liveData
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