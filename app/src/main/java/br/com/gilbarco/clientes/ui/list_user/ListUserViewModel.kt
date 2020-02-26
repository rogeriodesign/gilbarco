package br.com.gilbarco.clientes.ui.list_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gilbarco.clientes.model.model.User

class ListUserViewModel: ViewModel() {

    val users = MutableLiveData<List<User>>()

    fun getUsers(): LiveData<List<User>> {
        return users
    }
}