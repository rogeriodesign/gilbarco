package br.com.gilbarco.clientes.ui

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}