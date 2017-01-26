package com.example.mabisrror.javafundamentalsforandroid.clase09.form;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mabisrror.javafundamentalsforandroid.R;

public class FormActivity extends AppCompatActivity {

    EditText etemail, etpassword;
    Button btn;
    private final static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        init();
    }

    private void init() {
        etemail = (EditText)findViewById(R.id.etemail);
        etpassword = (EditText)findViewById(R.id.etpassword);
        btn = (Button)findViewById(R.id.btnlogin);

        etpassword.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                }
                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        if (isFormValidated()) {
            startActivity(new Intent(FormActivity.this, SuccessActivity.class));
        }
    }

    private boolean isFormValidated() {
        if (!isValidEmail()){
            Toast.makeText(this, getResources().getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidPassword()) {
            Toast.makeText(this, getResources().getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidPassword() {
        return !TextUtils.isEmpty(etpassword.getText()) && etpassword.getText().toString().matches(PASSWORD_REGEX);
    }

    public final boolean isValidEmail() {
        return !TextUtils.isEmpty(etpassword.getText()) && Patterns.EMAIL_ADDRESS.matcher(etemail.getText()).matches();
    }
}