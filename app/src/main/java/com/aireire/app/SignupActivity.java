package com.aireire.app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends RequiredFields {

    private UserDao userDao;
    private TextInputLayout firstNameView;
    private TextInputLayout lastNameView;
    private TextInputLayout emailView;
    private TextInputLayout passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setupToolbar();
        getFields();
        AppDatabase db = AppDatabase.getInstance(this);
        userDao = db.userDao();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_signup);
        toolbar.setTitleTextColor(getColor(R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getFields() {
        firstNameView = findViewById(R.id.first_name_entry_view);
        lastNameView = findViewById(R.id.last_name_entry_view);
        emailView = findViewById(R.id.email_entry_view);
        passwordView = findViewById(R.id.password_entry_view);
    }

    public void signupOnClick(View view) {

        String firstNameText = firstNameView.getEditText().getText().toString();
        String lastNameText = lastNameView.getEditText().getText().toString();
        String emailText = emailView.getEditText().getText().toString();
        String passwordText = passwordView.getEditText().getText().toString();

        if (!(areAnyFieldsEmpty())) {
            if (userDao.selectUserWithEmail(emailText) != null) {
                emailView.setError(getString(R.string.email_in_use_text));
            } else {
                User user = new User(firstNameText, lastNameText, emailText, passwordText);
                userDao.insertUser(user);
                Intent intent = new Intent(this, AccountHomeActivity.class);
                intent.putExtra(AccountHomeActivity.USER_EMAIL_INTENT, emailText);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean areAnyFieldsEmpty() {
        return isFieldEmpty(firstNameView) || isFieldEmpty(lastNameView) || isFieldEmpty(emailView) || isFieldEmpty(passwordView);
    }
}