package br.com.gilbarco.clientes.ui.register_user.list_country.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.Country

class ListCountryAdapter(val context: Context, private val listener: ((Country) -> Unit)? = null): RecyclerView.Adapter<ListCountryAdapter.CountryViewHolder>() {
    private val itemsList = ArrayList<Country>()
    var itemSelected: Country = Country(0,0,"","")
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
        holder.bind(country, itemSelected)
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.item_country_name)
        private val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)

        fun bind(country: Country, countrySelected: Country) {
            fillField(country)
            setCheck(country, countrySelected)
        }

        private fun fillField(country: Country) {
            name.text = country.name
        }

        private fun setCheck(country: Country, countrySelected: Country) {
            checkbox.isChecked = country.id == countrySelected.id

            if (listener == null) {
                checkbox.visibility = View.GONE
            } else {
                itemView.setOnClickListener {
                    notifyItemChanged(adapterPosition)
                    listener.invoke(country)
                }
            }
        }
    }
}