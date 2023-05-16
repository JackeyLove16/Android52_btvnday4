package com.darjeeling.android52_btvnday4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ActivityLifeCycle " + MainActivity.class.getName();
    private Button btnSignUp;
    private Button btnLogin;
    private EditText edtUserName;
    private EditText edtPasswords;

    public static final int REQUEST_CODE_SIGNUP = 123;
    public static final int REQUEST_CODE_HOME = 122;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        initView();

    }

    private void initView() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        edtUserName = findViewById(R.id.edtUserName);
        edtPasswords = findViewById(R.id.edtPasswords);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//                startActivity(intent);
//                startActivityForResult(intent, REQUEST_CODE_SIGNUP);
                activityResultLauncher.launch(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String userName = edtUserName.getText().toString();
                String passwords = edtPasswords.getText().toString();
                intent.putExtra(Constants.USER_NAME, userName);
                intent.putExtra(Constants.PASSWORDS, passwords);
                UserModel userModel = getIntent().getParcelableExtra("userModel");
                if (userModel != null) {
                    intent.putExtra("userModel", userModel);
                }
                startActivity(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Log.d(TAG, "onActivityResult: " + result.getResultCode());
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            UserModel userModel = (UserModel) intent.getSerializableExtra(Constants.USER);
                            Log.d(TAG, "onActivityResult: " + userModel.toString());
                        }
                    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGNUP) {
            if (resultCode == RESULT_OK) {
                UserModel userModel = (UserModel) data.getSerializableExtra(Constants.USER);
                Log.d(TAG, "onActivityResult: " + userModel.toString());
                edtUserName.setText(userModel.getUsername());
                edtPasswords.setText(userModel.getPassword());
            }
            if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: User cancel");
            }
        }
        if (requestCode == REQUEST_CODE_HOME) {

        }
    }
}