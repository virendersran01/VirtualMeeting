package com.virtualstudios.virtualmeeting.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.virtualstudios.virtualmeeting.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.imageBack).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.textSignIn).setOnClickListener(view -> onBackPressed());
    }
}