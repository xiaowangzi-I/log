package com.example.first;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText mEtUserName;
    private TextInputEditText mEtPassword;
    private Button mBtnLogin;
    private Button mBtntoResign;
    private Save save;
    private CheckBox mCheckBox;

    private String storeUsername;
    private String storePassword;
    private TextInputLayout mtilUserpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        save = new Save(this);
        initView();
        initEvent();
        boolean checkbox = save.getCheckbox();
        mCheckBox.setChecked(checkbox);
    }
    
    private void initView() {
        mEtUserName = findViewById(R.id.username);
        mEtPassword = findViewById(R.id.userpassword);
        mBtnLogin = findViewById(R.id.login);
        mBtntoResign = findViewById(R.id.toresign);
        mCheckBox = findViewById(R.id.store);
        mtilUserpassword = findViewById(R.id.til_password);
    }
    
    private void initEvent() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mBtntoResign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toresign();
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    save.setCheckbox(true);
                    save();
                    store();
                }else {
                    save.setCheckbox(false);
                }
            }
        });

        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if(isValidInput(input)) {
                    mtilUserpassword.setError(null);
                }else{
                    mtilUserpassword.setError(" ");
                }
            }
        });
    }

    private void login() {
        save();
        String log_name = mEtUserName.getText().toString();
        String log_password = mEtPassword.getText().toString();
        if (log_name.equals(storeUsername)&&log_password.equals(storePassword)) {
            loginsuc();
        }else {
            loginfai();
        }
    }

    private void loginsuc() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        intent.putExtra("username",mEtUserName.getText().toString());
        startActivity(intent);
    }

    private void loginfai() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }

    private void toresign() {
        Intent intent = new Intent(LoginActivity.this,ResignActivity.class);
        startActivity(intent);
    }

    private void save() {
        storeUsername = save.getUsername();
        storePassword = save.getPassword();
    }

    private void store() {
            mEtUserName.setText(storeUsername);
            mEtPassword.setText(storePassword);
    }

    private boolean isValidInput(String input) {
        if(input.length()<=6) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for(char c:input.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
            else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        if (hasLetter && hasDigit) {
            return true;
        }
        return false;
    }
}