package com.oddhov.signupformvalidation.ui

import javax.inject.Inject

class MainPresenter<T : MainView> @Inject constructor() {

    private var view : MainView? = null

    private var emailContainsText = false
    private var passwordContainsText = false

    fun attachView(mvpView: T) {
        this.view = mvpView

        updateContinueButtonState()
    }

    fun detachView() {
        view = null
    }

    fun onEmailTextChanged(text: CharSequence) {
        emailContainsText = !text.isEmpty()
        updateContinueButtonState()
    }

    fun onPasswordTextChanged(text: CharSequence) {
        passwordContainsText = !text.isEmpty()
        updateContinueButtonState()
    }

    fun onContinueClicked() {
        if (view!!.isEmailValid() && view!!.isPasswordValid()) {
            view!!.showToast("Account created")
        } else {
            view!!.forceUpdateEmailPassword()
        }
    }
    // endregion

    // region Helper methods
    private fun updateContinueButtonState() {
        if (emailContainsText && passwordContainsText) {
            view!!.enableContinueButton()
        } else {
            view!!.disableContinueButton()
        }
    }
    // endregion
}
