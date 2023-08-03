package com.example.cinematicapp.repository.utils

import com.google.android.material.textfield.TextInputLayout

object ViewUtils {

    fun TextInputLayout.validatePhone(edText: String): Boolean {
        return if (edText.trim().isEmpty()) {
            this.error = Constants.Validate.ERROR_EMPTY_EDIT_TEXT
            false
        } else {
            if (edText.length != 12 || !edText.startsWith(Constants.Validate.VALIDATE_NUMBER)) {
                this.error = Constants.Validate.ERROR_VALIDATE_NUMBER_SIZE
                false
            } else {
                this.isErrorEnabled = false
                true
            }
        }
    }

    fun TextInputLayout.validatePass(edText: String): Boolean {
        return if (edText.trim().isEmpty()) {
            this.error = Constants.Validate.ERROR_EMPTY_EDIT_TEXT
            false
        } else {
            this.isErrorEnabled = false
            true
        }
    }

    fun TextInputLayout.validate(edText: String): Boolean {
        return if (edText.trim().isEmpty()) {
            this.error = Constants.Validate.ERROR_EMPTY_EDIT_TEXT
            false
        } else {
            this.isErrorEnabled = false
            true
        }
    }
}