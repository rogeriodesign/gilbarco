package br.com.gilbarco.clientes.ui.list_country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.Country
import br.com.gilbarco.clientes.presenter.CountryPresenter
import br.com.gilbarco.clientes.ui.alert
import br.com.gilbarco.clientes.ui.list_country.adapter.ListCountryAdapter
import kotlinx.android.synthetic.main.fragment_list_users.view.*

class ListCountryFragment : Fragment() {
    private lateinit var model: ListCountryViewModel
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
        model = ViewModelProvider(this, ListCountryViewModelFactory()).get(ListCountryViewModel::class.java)
    }

    private fun setRecyclerView(root: View) {
        model.getCountries().observe(viewLifecycleOwner, Observer { contriesFound ->
            contriesFound?.let {
                setAdapter(it, root.lista_users_recyclerview as RecyclerView)
            }
        })
    }

    private fun setAdapter(contries: List<Country>, rw: RecyclerView) {
        context?.let {
            val adapter = ListCountryAdapter(it, contries)
            rw.adapter = adapter
        }
    }

    private fun getAllUsers(){
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