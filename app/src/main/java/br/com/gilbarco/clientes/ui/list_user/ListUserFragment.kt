package br.com.gilbarco.clientes.ui.list_user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.User
import br.com.gilbarco.clientes.presenter.UserPresenter
import br.com.gilbarco.clientes.ui.alert
import br.com.gilbarco.clientes.ui.list_user.adapter.ListUsersAdapter
import kotlinx.android.synthetic.main.fragment_list_users.view.*

class ListUserFragment : Fragment() {
    private lateinit var model: ListUserViewModel
    private lateinit var presenter: UserPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list_users, container, false)
        context?.let {
            presenter = UserPresenter(it)
        }

        setViewModel()
        getAllUsers()
        setRecyclerView(root)
        return root
    }

    private fun setViewModel() {
        model = ViewModelProvider(this, ListUserViewModelFactory()).get(ListUserViewModel::class.java)
    }

    private fun setRecyclerView(root: View) {
        model.getUsers().observe(viewLifecycleOwner, Observer { usersFound ->
            Log.i("list user", usersFound.toString())
            if(usersFound != null && usersFound.isNotEmpty()){
                Log.i("list user", "tem lista")
                setAdapter(usersFound, root.lista_users_recyclerview as RecyclerView)
                root.list_no_item.visibility = View.GONE
            } else {
                Log.i("list user", "não tem lista")
                root.list_no_item.visibility = View.VISIBLE
            }
        })
    }

    private fun setAdapter(users: List<User>, rw: RecyclerView) {
        context?.let {
            val adapter = ListUsersAdapter(it)
            adapter.items = users
            rw.adapter = adapter
        }
    }

    private fun getAllUsers(){
        presenter.getAllWithCountry().observe(viewLifecycleOwner, Observer {
            if (it.error == null) {
                model.users.postValue(it.data)
                //setRecyclerView()
            } else {
                alert("Falha", "Não foi possivel carregar os clientes")
            }
        })
    }


}