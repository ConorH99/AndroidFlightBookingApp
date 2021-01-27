package com.aireire.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    UserDatabase db;
    UserDao userDao;
    TextInputLayout emailView;
    TextInputLayout passwordView;
    String emailText;
    String passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = findViewById(R.id.email_entry_view);
        passwordView = findViewById(R.id.password_entry_view);
        Toolbar toolbar = findViewById(R.id.toolbar_login);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        db = UserDatabase.getInstance(this);
        userDao = db.userDao();
    }

    public void loginOnClick(View view) {
        emailText = emailView.getEditText().getText().toString();
        passwordText = passwordView.getEditText().getText().toString();
        if (!(areAnyFieldsEmpty())) {
            User user = userDao.selectUserWithEmail(emailText);
            if (user == null) {
                emailView.setError(getString(R.string.account_doesnt_exist_text));
            } else {
                Intent intent = new Intent(this, AccountHomeActivity.class);
                intent.putExtra(AccountHomeActivity.USER_INFO, emailText);
                startActivity(intent);

            }
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
        if (!(isFieldEmpty(emailView) || isFieldEmpty(passwordView))) {
            return false;
        }
        return true;
    }
}