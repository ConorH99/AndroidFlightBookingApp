package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    UserDatabase db;
    UserDao userDao;
    TextInputLayout emailView = findViewById(R.id.email_entry_view);
    TextInputLayout passwordView = findViewById(R.id.password_entry_view);
    String emailText = emailView.getEditText().getText().toString();
    String passwordText = passwordView.getEditText().getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar_login);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        db = UserDatabase.getInstance(this);
        userDao = db.userDao();
    }

    public void loginOnClick(View view) {
        System.out.println(areAnyFieldsEmpty());
        //TODO Log in functionality
    }

    private boolean isFieldEmpty(TextInputLayout textBox) {
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

    private boolean areAnyFieldsEmpty() {
        if (!(isFieldEmpty(emailView) || isFieldEmpty(passwordView))) {
            return false;
        }
        return true;
    }
}