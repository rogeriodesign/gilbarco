package br.com.gilbarco.clientes.ui.validator

import CNPJValidator
import android.widget.EditText
import androidx.annotation.NonNull
import com.google.android.material.textfield.TextInputLayout

class ValidatesCnpj(private val textInputCnpj: TextInputLayout) : Validator {
    val CNPJ_INVALIDO = "CNPJ inválido"
    val DEVE_TER_QUATORZE_DIGITOS = "O CNPJ precisa ter 14 dígitos"
    private val formatador = CNPJormatter()
    private var fieldCnpj: EditText = textInputCnpj.editText!!
    private var validatesDefault: ValidatesDefault = ValidatesDefault(textInputCnpj)

    private fun validCalcCnpj(cnpj: String): Boolean {

        val cnpjValidator = CNPJValidator()
        if (cnpjValidator.assertValid(cnpj)) {
            return true
        } else {
            textInputCnpj.error = CNPJ_INVALIDO
            return false
        }
    }

    private fun validFieldElevenDigits(cnpj: String): Boolean {
        if (cnpj.length != 14) {
            textInputCnpj.error = DEVE_TER_QUATORZE_DIGITOS
            return false
        }
        return true
    }

    override fun statusValid(): Boolean {
        if (!validatesDefault.statusValid()) return false
        val cnpj = getCnpj()
        var cnpjNoFormat = cnpj
        try {
            cnpjNoFormat = formatador.unformat(cnpj)
        } catch (e: Exception) {
        }

        if (!validFieldElevenDigits(cnpjNoFormat)) return false
        if (!validCalcCnpj(cnpjNoFormat)) return false
        addFormat(cnpjNoFormat)
        return true
    }

    private fun addFormat(cnpj: String) {
        val cnpjFormated = formatador.format(cnpj)
        fieldCnpj.setText(cnpjFormated)
    }

    @NonNull
    private fun getCnpj(): String {
        return fieldCnpj.text.toString()
    }
}
