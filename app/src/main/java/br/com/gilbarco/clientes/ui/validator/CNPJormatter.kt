package br.com.gilbarco.clientes.ui.validator

import java.util.regex.Pattern

class CNPJormatter {
    private var base: BaseFormatter =
        BaseFormatter(FORMATED, "$1.$2.$3/$4-$5", UNFORMATED, "$1$2$3$4$5")

    fun format(value: String): String {//51.441.528/0001-37
        return base.format(value)
    }

    fun unformat(value: String): String {
        return base.unformat(value)
    }

    companion object {
        val FORMATED = Pattern.compile("(\\d{2})[.](\\d{3})[.](\\d{3})/(\\d{4})[-](\\d{2})")
        val UNFORMATED = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})")
    }
}