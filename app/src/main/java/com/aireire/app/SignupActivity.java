package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    UserDatabase db;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar_signup);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        db = UserDatabase.getInstance(this);
        userDao = db.userDao();
    }

    public void signupOnClick(View view) {
        TextInputLayout firstNameView = findViewById(R.id.first_name_entry_view);
        checkIfEmpty(firstNameView);
        TextInputLayout lastNameText = findViewById(R.id.last_name_entry_view);
        checkIfEmpty(lastNameText);
        TextInputLayout emailView = findViewById(R.id.email_entry_view);
        checkIfEmpty(emailView);
        TextInputLayout passwordView = findViewById(R.id.password_entry_view);
        checkIfEmpty(passwordView);
    }

    private void checkIfEmpty(TextInputLayout textBox) {
        if (textBox.getError() != null) {
            textBox.setError(null);
        }
        String enteredText = textBox.getEditText().getText().toString();
        if (TextUtils.isEmpty(enteredText)) {
            textBox.setError(getString(R.string.required_text));
        }
    }
}