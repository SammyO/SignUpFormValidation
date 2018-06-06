package com.rubicoin.invest.tools.validator

import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmailValidator @Inject constructor() {

    private val validEmailAddressRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    fun isValid(emailStr: String): Boolean {
        val matcher = validEmailAddressRegex.matcher(emailStr)
        return matcher.find()
    }
}
