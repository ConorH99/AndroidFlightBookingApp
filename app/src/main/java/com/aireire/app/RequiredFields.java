package com.aireire.app;

import android.content.Context;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public abstract class RequiredFields extends AppCompatActivity {

    public boolean isFieldEmpty(TextInputLayout textBox) {
        if (textBox.getError() != null) {
            textBox.setError(null);
        }
        String enteredText = textBox.getEditText().getText().toString();
        if (TextUtils.isEmpty(enteredText)) {
            textBox.setError(getString(R.string.required_text));
            return true;
        }
        return false;
    }

    public abstract boolean areAnyFieldsEmpty();
}
