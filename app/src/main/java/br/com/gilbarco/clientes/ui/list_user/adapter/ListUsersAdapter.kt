package br.com.gilbarco.clientes.ui.list_user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gilbarco.clientes.R
import br.com.gilbarco.clientes.model.model.User

class ListUsersAdapter(val context: Context, private var users: List<User>): RecyclerView.Adapter<ListUsersAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val viewCreate = LayoutInflater.from(context)
            .inflate(R.layout.item_list_user, parent, false)
        return UserViewHolder(viewCreate)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.item_user_name)
        private val cnpj: TextView = itemView.findViewById(R.id.item_user_cnpj)

        fun bind(user: User) {
            fillField(user)
        }

        private fun fillField(user: User) {
            name.text = user.name
            cnpj.text = user.cnpj
        }
    }
}