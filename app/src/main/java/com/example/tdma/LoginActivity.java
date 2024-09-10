package com.example.tdma;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail;
    EditText edPassword;
    Button btn;
    TextView tv;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.Email);
        edPassword = findViewById(R.id.Password);
        btn = findViewById(R.id.button);
        tv = findViewById(R.id.RegUser);

        sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "TMD", null, 1);

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(email, password) == 1) {
                        // Get the username associated with the email
                        String username = db.getUsernameByEmail(email);
                        if (username != null) {
                            Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                            // Save the username in SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", username);
                            editor.apply();

                            // Navigate to HomeActivity
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish(); // Finish LoginActivity
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to retrieve username", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
