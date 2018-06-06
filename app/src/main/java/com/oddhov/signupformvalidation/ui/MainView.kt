package com.oddhov.signupformvalidation.ui

interface MainView {
    fun isEmailValid(): Boolean
    fun isPasswordValid(): Boolean
    fun forceUpdateEmailPassword()
    fun enableContinueButton()
    fun disableContinueButton()
    fun showToast(message: String)
}