package com.darjeeling.android52_btvnday4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "ActivityLifeCycle " + SignUpActivity.class.getName();
    private Button btnSignUp;
    private EditText edtUserName;
    private EditText edtPasswords;
    private EditText edtAddress;
    private EditText edtEmail;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d(TAG, "onCreate: ");

        initView();
    }

    private void initView() {
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUserName = findViewById(R.id.edtUserName);
        edtPasswords = findViewById(R.id.edtPasswords);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                String userName = edtUserName.getText().toString();
                String passwords = edtPasswords.getText().toString();
                String address = edtAddress.getText().toString();
                String email = edtEmail.getText().toString();

                UserModel userModel = new UserModel(userName, passwords, address, email);
                intent.putExtra(Constants.USER, userModel);
                startActivity(intent);
            }
        });

    }

    private void saveRegistrationInfo(String userName, String passwords, String email, String address) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", userName);
        editor.putString("password", passwords);
        editor.putString("email", email);
        editor.putString("address", address);
        editor.apply();
    }
}
