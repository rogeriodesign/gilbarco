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
import br.com.gilbarco.clientes.ui.RegisterUserViewModel
import br.com.gilbarco.clientes.ui.RegisterUserViewModelFactory
import br.com.gilbarco.clientes.ui.alert
import br.com.gilbarco.clientes.ui.list_user.adapter.ListUsersAdapter
import kotlinx.android.synthetic.main.fragment_list_users.view.*

class ListUserFragment : Fragment() {
    private lateinit var model: RegisterUserViewModel
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

        setViewModel(root)
        getAllUsers()
        return root
    }

    private fun setViewModel(root: View) {
        activity?.let {activity ->
            context?.let{
                model = ViewModelProvider(activity,
                    RegisterUserViewModelFactory(it)
                ).get(RegisterUserViewModel::class.java)
                model.init()
            }
        }
        model.usersResult.observe(viewLifecycleOwner, Observer { resource ->
            if (resource.error == null) {
                setRecyclerView(root, resource.data)
            } else {
                alert("Falha", resource.error)
            }
        })
    }

    private fun setRecyclerView(root: View, usersFound: List<User>?) {
        if (usersFound != null) {
            setAdapter(usersFound, root.lista_users_recyclerview as RecyclerView)
            root.list_no_item.visibility = View.GONE
        } else {
            root.list_no_item.visibility = View.VISIBLE
        }
    }

    private fun setAdapter(users: List<User>, rw: RecyclerView) {
        context?.let {
            val adapter = ListUsersAdapter(it)
            adapter.items = users
            rw.adapter = adapter
        }
    }

    private fun getAllUsers(){
        model.getUsers()
    }
}