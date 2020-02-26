package br.com.gilbarco.clientes.ui.validator

import java.util.regex.Matcher
import java.util.regex.Pattern

class BaseFormatter(
    private val formatted: Pattern,
    private val formattedReplacement: String,
    private val unformatted: Pattern,
    private val unformattedReplacement: String
) {

    fun format(value: String?): String {
        val result: String
        requireNotNull(value) { "Value may not be null." }
        val matcher = unformatted.matcher(value)
        result = matchAndReplace(matcher, formattedReplacement)
        return result
    }

    fun unformat(value: String?): String {
        val result: String
        requireNotNull(value) { "Value may not be null." }

        val unformattedMatcher = unformatted.matcher(value)
        if (unformattedMatcher.matches()) {
            return value
        }

        val matcher = formatted.matcher(value)
        result = matchAndReplace(matcher, unformattedReplacement)
        return result
    }

    private fun matchAndReplace(matcher: Matcher, replacement: String): String {
        var result: String
        if (matcher.matches()) {
            result = matcher.replaceAll(replacement)
        } else {
            throw IllegalArgumentException("Value is not properly formatted.")
        }
        return result
    }
}