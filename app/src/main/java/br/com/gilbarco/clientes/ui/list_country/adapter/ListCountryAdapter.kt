package br.com.gilbarco.clientes.ui.list_country.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.Country

class ListCountryAdapter(val context: Context, private var countries: List<Country>): RecyclerView.Adapter<ListCountryAdapter.CountryViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val viewCreate = LayoutInflater.from(context)
            .inflate(R.layout.item_list_country, parent, false)
        return CountryViewHolder(viewCreate)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.item_country_name)

        fun bind(country: Country) {
            fillField(country)
        }

        private fun fillField(country: Country) {
            name.text = country.name
        }
    }
}