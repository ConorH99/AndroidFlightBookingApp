package com.aireire.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends RequiredFields {

    private UserDao userDao;
    private TextInputLayout emailView;
    private TextInputLayout passwordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupToolbar();
        getFields();
        AppDatabase db = AppDatabase.getInstance(this);
        userDao = db.userDao();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_login);
        toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getFields() {
        emailView = findViewById(R.id.email_entry_view);
        passwordView = findViewById(R.id.password_entry_view);
    }

    public void loginOnClick(View view) {
        String emailText = emailView.getEditText().getText().toString();
        if (!(areAnyFieldsEmpty())) {
            User user = userDao.selectUserWithEmail(emailText);
            if (checkEmailExists(user) && checkPassword(user)) {
                Intent intent = new Intent(this, AccountHomeActivity.class);
                intent.putExtra(AccountHomeActivity.USER_EMAIL_INTENT, emailText);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean areAnyFieldsEmpty() {
        return isFieldEmpty(emailView) || isFieldEmpty(passwordView);
    }

    private boolean checkEmailExists(User user) {
        if (user == null) {
            emailView.setError(getString(R.string.account_doesnt_exist_text));
            return false;
        }
        return true;
    }

    private boolean checkPassword(User user) {
        String passwordText = passwordView.getEditText().getText().toString();
        if (passwordText.equals(user.password)) {
            return true;
        }
        passwordView.setError(getString(R.string.password_incorrect_text));
        return false;
    }
}