package br.com.gilbarco.clientes.ui.register_user.list_country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.presenter.CountryPresenter
import br.com.gilbarco.clientes.ui.alert
import br.com.gilbarco.clientes.ui.register_user.RegisterUserViewModel
import br.com.gilbarco.clientes.ui.register_user.RegisterUserViewModelFactory
import br.com.gilbarco.clientes.ui.register_user.list_country.adapter.ListCountryAdapter
import kotlinx.android.synthetic.main.fragment_list_countries.*
import kotlinx.android.synthetic.main.fragment_list_countries.view.*

class ListCountryFragment : Fragment() {
    private lateinit var model: RegisterUserViewModel
    private lateinit var presenter: CountryPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list_countries, container, false)
        context?.let {
            presenter = CountryPresenter(it)
        }

        setViewModel()
        getAllUsers()
        setRecyclerView(root)
        return root
    }

    private fun setViewModel() {
        activity?.let {
            model = ViewModelProvider(
                it,
                RegisterUserViewModelFactory()
            ).get(RegisterUserViewModel::class.java)
        }

    }

    private fun setRecyclerView(root: View) {
        model.getCountries().observe(viewLifecycleOwner, Observer { contriesFound ->
            if (contriesFound != null) {
                setAdapter(contriesFound, root.lista_countries_recyclerview as RecyclerView)
                list_no_item.visibility = View.GONE
            } else {
                list_no_item.visibility = View.VISIBLE
            }
        })
    }

    private fun setAdapter(contries: List<Country>, rw: RecyclerView) {
        context?.let {
            val adapter = ListCountryAdapter(it) { country ->
                model.countrySelected.postValue(country)
                findNavController().navigate(R.id.action_nav_list_countries_to_nav_add_user)
            }.apply {
                items = contries
            }
            rw.adapter = adapter
        }
    }

    private fun getAllUsers() {
        presenter.getAll().observe(viewLifecycleOwner, Observer {
            if (it.error == null) {
                model.countries.postValue(it.data)
                //setRecyclerView()
            } else {
                alert("Falha", "Não foi possivel carregar os países")
            }
        })
    }


}