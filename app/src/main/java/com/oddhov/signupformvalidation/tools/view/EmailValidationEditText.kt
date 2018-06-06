package com.oddhov.signupformvalidation.tools.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import com.oddhov.signupformvalidation.App
import com.oddhov.signupformvalidation.R
import com.oddhov.signupformvalidation.injection.ApplicationModule
import com.rubicoin.invest.tools.validator.EmailValidator
import javax.inject.Inject

class EmailValidationEditText : EditText {
    @Inject lateinit var emailValidator: EmailValidator

    private val stateError = intArrayOf(R.attr.state_error)
    private var hasBeenEdited = false
    private var shouldShowError = false
    private var hasFocus = false

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 2)
        if (shouldShowError) {
            View.mergeDrawableStates(drawableState, stateError)
        }
        return drawableState
    }

    fun forceUpdateState() {
        shouldShowError = !emailValidator.isValid(text.toString())
        refreshDrawableState()
    }

    fun isValid(): Boolean {
        return emailValidator.isValid(text.toString())
    }

    private fun init() {
        App.get(context).applicationComponent.inject(this)

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                hasBeenEdited = true
            }
        })

        onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            this.hasFocus = hasFocus
            updateState()
        }
    }

    private fun updateState() {
        shouldShowError = !hasFocus && !emailValidator.isValid(text.toString()) && hasBeenEdited
        refreshDrawableState()
    }
}
