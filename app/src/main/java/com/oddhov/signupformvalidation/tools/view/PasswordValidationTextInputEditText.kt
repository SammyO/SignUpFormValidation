package com.oddhov.signupformvalidation.tools.view

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.View.OnFocusChangeListener
import com.oddhov.signupformvalidation.App
import com.oddhov.signupformvalidation.R
import com.rubicoin.invest.tools.validator.PasswordValidator
import javax.inject.Inject

class PasswordValidationTextInputEditText : TextInputEditText {
    @Inject lateinit var passwordValidator: PasswordValidator

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
        shouldShowError = !passwordValidator.isValid(text.toString())
        refreshDrawableState()
    }

    fun isValid(): Boolean {
        return passwordValidator.isValid(text.toString())
    }

    private fun init() {
        App.get(context).applicationComponent.inject(this)

        onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            this.hasFocus = hasFocus
            updateState()
        }

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int,
                                       before: Int, count: Int) {
                hasBeenEdited = true
                updateState()
            }
        })
    }

    private fun updateState() {
        shouldShowError = !hasFocus && !passwordValidator.isValid(text.toString()) && hasBeenEdited
        refreshDrawableState()
    }
}
