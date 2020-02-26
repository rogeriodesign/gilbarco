package br.com.gilbarco.clientes.ui.list_country.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.Country

class ListCountryAdapter(val context: Context, private val listener: ((Country) -> Unit)? = null): RecyclerView.Adapter<ListCountryAdapter.CountryViewHolder>() {
    private val itemsList = ArrayList<Country>()
    var items: List<Country>?
        get() = itemsList
        set(value) {
            itemsList.clear()
            if (value != null && value.isNotEmpty()) {
                itemsList.addAll(value)
            }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val viewCreate = LayoutInflater.from(context)
            .inflate(R.layout.item_list_country, parent, false)
        return CountryViewHolder(viewCreate)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = itemsList[position]
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

        checkbox.isChecked = country.selected
        checkbox.setOnClickListener {
            callOnClick()
        }

        if (listener == null) {
            checkbox.gone()
        } else {
            setOnClickListener {
                country.selected = country.selected.not()
                notifyItemChanged(adapterPosition)
                listener.invoke(country)
            }
        }
    }
}