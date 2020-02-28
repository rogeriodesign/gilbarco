package br.com.gilbarco.clientes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gilbarco.clientes.model.Resource
import br.com.gilbarco.clientes.model.model.User
import br.com.gilbarco.clientes.presenter.UserPresenter

class UserViewModel(private val userPresenter: UserPresenter): ViewModel(),
    UserContract.ViewImpl {

    val user = MutableLiveData<User>()
    private val _saveResult = MutableLiveData<Resource<String>>()
    val saveResult: LiveData<Resource<String>> = _saveResult
    private val _usersResult = MutableLiveData<Resource<List<User>?>>()
    val usersResult: LiveData<Resource<List<User>?>> = _usersResult


    override fun save(user: User){
        userPresenter.save(user)
    }

    override fun responseSave(resource: Resource<Void?>) {
        if (resource.error == null) {
            user.value = null
            _saveResult.value = Resource(data= "Cliente ${user.value?.name} gravado com sucesso")
        } else {
            _saveResult.value = Resource(data= null, error = "Não foi possível gravar o cliente ${user.value?.name}.\n ${resource.error}")
        }
    }

    override fun getUsers() {
        userPresenter.getAll()
    }

    override fun setList(resource: Resource<List<User>?>) {
        if (resource.error == null) {
            _usersResult.value = resource
        } else {
            _usersResult.value = Resource(data= null, error = "Não foi possível carregar os clientes.\n${resource.error}")
        }
    }

    fun init(){
        userPresenter.setView(this)
    }

    fun getUser(): LiveData<User> {
        return user
    }

    fun resetSaveResult(){
        _saveResult.value = null
    }

    fun setName(text : String){
        if(user.value != null) {
            user.value!!.name = text
        } else {
            user.value = User(name = text)
        }
    }

    fun setCode(text : String){
        if(text!=null && text.isNotEmpty()) {
            if (user.value != null) {
                user.value!!.code = text.toLong()
            } else {
                user.value = User(code = text.toLong())
            }
        }
    }

    fun setCnpj(text : String){
        if(user.value != null) {
            user.value!!.cnpj = text
        } else {
            user.value = User(cnpj = text)
        }
    }
}