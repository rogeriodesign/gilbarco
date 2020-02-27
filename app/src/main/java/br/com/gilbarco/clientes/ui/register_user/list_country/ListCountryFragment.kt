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
import br.com.gilbarco.clientes.ui.RegisterCountryViewModel
import br.com.gilbarco.clientes.ui.RegisterCountryViewModelFactory
import br.com.gilbarco.clientes.ui.alert
import br.com.gilbarco.clientes.ui.RegisterUserViewModelFactory
import br.com.gilbarco.clientes.ui.register_user.list_country.adapter.ListCountryAdapter
import kotlinx.android.synthetic.main.fragment_list_countries.*
import kotlinx.android.synthetic.main.fragment_list_countries.view.*

class ListCountryFragment : Fragment() {
    private lateinit var model: RegisterCountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list_countries, container, false)

        setViewModel(root)
        getAllContries()
        return root
    }

    private fun setViewModel(root: View) {
        activity?.let { activity ->
            context?.let {
                model = ViewModelProvider(
                    activity,
                    RegisterCountryViewModelFactory(it)
                ).get(RegisterCountryViewModel::class.java)
                model.init()
            }
        }
        model.countriesResult.observe(viewLifecycleOwner, Observer { resource ->
            if (resource.error == null) {
                setRecyclerView(root, resource.data)
            } else {
                alert("Falha", resource.error)
            }
        })
    }

    private fun setRecyclerView(root: View, contriesFound: List<Country>?) {
        if (contriesFound != null) {
            setAdapter(contriesFound, root.lista_countries_recyclerview as RecyclerView)
            list_no_item.visibility = View.GONE
        } else {
            list_no_item.visibility = View.VISIBLE
        }
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

    private fun getAllContries() {
        model.getCountries()
    }
}
