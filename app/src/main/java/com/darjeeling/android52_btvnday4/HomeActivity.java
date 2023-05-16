package com.darjeeling.android52_btvnday4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_home);
        initView();
    }

    private void initView() {
        tvInfo = findViewById(R.id.tvInfo);
        Intent intent = getIntent();
        String username = intent.getStringExtra(Constants.USER_NAME);
        String password = intent.getStringExtra(Constants.PASSWORDS);
        UserModel userModel = intent.getParcelableExtra(Constants.USER);
        String email = userModel.getEmail();
        String address = userModel.getAddress();
        tvInfo.setText(" user name : " + username + " passwords : " + password+"adress:"+address+"mail:"+email);

        Log.d(TAG, "initView: " + userModel.toString());

    }

}
