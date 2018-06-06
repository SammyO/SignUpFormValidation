package com.oddhov.signupformvalidation.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.oddhov.signupformvalidation.App
import com.oddhov.signupformvalidation.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
    @Inject
    lateinit var presenter: MainPresenter<MainView>

    // region Activity Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        setContentView(R.layout.activity_main)

        emailAddress.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int,
                                       before: Int, count: Int) {
                presenter.onEmailTextChanged(text)
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int,
                                       before: Int, count: Int) {
                presenter.onPasswordTextChanged(text)
            }
        })

        continueButton.setOnClickListener { presenter.onContinueClicked() }

        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
    // endregion

    // region MainView methods
    override fun isEmailValid(): Boolean {
        return emailAddress.isValid()
    }

    override fun isPasswordValid(): Boolean {
        return password.isValid()
    }

    override fun forceUpdateEmailPassword() {
        emailAddress.forceUpdateState()
        password.forceUpdateState()
    }

    override fun enableContinueButton() {
        continueButton.isEnabled = true
    }

    override fun disableContinueButton() {
        continueButton.isEnabled = false
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    // endregion

    // region Helper methods
    private fun inject() {
        App.get(this).applicationComponent.plus(MainActivityComponent.MainActivityModule(this)).inject(this)
    }
    // endregion
}
