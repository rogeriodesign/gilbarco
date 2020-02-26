package br.com.gilbarco.clientes.ui.validator

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class ValidatesDefault(private val textInputField: TextInputLayout) : Validator {
    private val field: EditText

    init {
        this.field = this.textInputField.editText!!
    }

    private fun validateFieldRequired(): Boolean {
        val text = field.text.toString()
        if (text.isEmpty()) {
            textInputField.error = FIELD_REQUIRED
            return false
        }
        return true
    }

    override fun statusValid(): Boolean {
        if (!validateFieldRequired()) return false
        removeError()
        return true
    }

    private fun removeError() {
        textInputField.error = null
        textInputField.isErrorEnabled = false
    }

    companion object {
        private const val FIELD_REQUIRED = "Campo obrigatório"
    }

}