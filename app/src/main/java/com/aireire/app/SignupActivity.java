package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    UserDatabase db;
    UserDao userDao;
    TextInputLayout firstNameView = findViewById(R.id.first_name_entry_view);
    TextInputLayout lastNameView = findViewById(R.id.last_name_entry_view);
    TextInputLayout emailView = findViewById(R.id.email_entry_view);
    TextInputLayout passwordView = findViewById(R.id.password_entry_view);
    String firstNameText = firstNameView.getEditText().getText().toString();
    String lastNameText = lastNameView.getEditText().getText().toString();
    String emailText = emailView.getEditText().getText().toString();
    String passwordText = passwordView.getEditText().getText().toString();

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

        if (!(areAnyFieldsEmpty())) {
            User user = new User(firstNameText, lastNameText, emailText, passwordText);
            userDao.insertUser(user);
        }
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

        if (!(isFieldEmpty(firstNameView) || isFieldEmpty(lastNameView) || isFieldEmpty(emailView) || isFieldEmpty(passwordView))) {
            return false;
        }
        return true;
    }
}