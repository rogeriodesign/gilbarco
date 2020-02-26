package br.com.gilbarco.clientes.ui

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment


inline fun Fragment.alert(title: String, message: String) {
    this.context?.let {
        AlertDialog.Builder(it)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") { dialogInterface, i ->
                /*val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val alunoEscolhido = adapter.getItem(menuInfo.position)
                remove(alunoEscolhido)*/
            }
            //.setNegativeButton("Não", null)
            .show()
    }

}