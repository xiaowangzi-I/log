package com.example.first;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ResignActivity extends AppCompatActivity {
    private EditText metusername;
    private EditText metpassword;
    private Button mbtnResign;
    private Button mbtnToLogin;
    private TextInputLayout mtilUserpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);
        initView();
        initEvent();
    }

    private void initView() {
        metusername = findViewById(R.id.reign_name);
        metpassword = findViewById(R.id.resign_word);
        mbtnResign = findViewById(R.id.resign);
        mbtnToLogin = findViewById(R.id.tologin);
        mtilUserpassword = findViewById(R.id.til_password);
    }

    private void initEvent() {
        mbtnResign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resign();
            }
        });

        mbtnToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tologin();
            }
        });

        metpassword.addTextChangedListener(new TextWatcher() {
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

    private void resign() {
        String username = metusername.getText().toString();
        String password = metpassword.getText().toString();
        String error = mtilUserpassword.getError().toString();
        if (isEmpty(username)||isEmpty(password)||!isEmpty(error)) {
            resignfai();
        }else{
            resignsuc();
        }
    }

    private void resignsuc() {
        String username = metusername.getText().toString();
        String password = metpassword.getText().toString();
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        Save save = new Save(this);
        save.setUsername(username);
        save.setPassword(password);
        Intent intent = new Intent(ResignActivity.this,HomeActivity.class);
        intent.putExtra("username",metusername.getText().toString());
        startActivity(intent);
    }

    private void resignfai() {
        Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
    }

    private void tologin() {
        Intent intent = new Intent(ResignActivity.this,LoginActivity.class);
        startActivity(intent);
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