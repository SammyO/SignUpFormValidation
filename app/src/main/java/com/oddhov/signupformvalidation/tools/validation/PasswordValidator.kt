package com.rubicoin.invest.tools.validator

import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordValidator @Inject constructor() {

    private val atLeastOneNumberPattern = Pattern.compile(".*[0-9].*")

    fun isValid(password: String): Boolean {
        if (password.length < 8) {
            return false
        }
        if (password.length > 90) {
            return false
        }
        if (!atLeastOneNumberPattern.matcher(password).find()) {
            return false
        }
        return true
    }
}
