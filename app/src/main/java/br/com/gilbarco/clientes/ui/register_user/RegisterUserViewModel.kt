package br.com.gilbarco.clientes.ui.register_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gilbarco.clientes.model.model.User

class RegisterUserViewModel: ViewModel() {

    val user = MutableLiveData<User>()

    fun getUser(): LiveData<User> {
        return user
    }
}