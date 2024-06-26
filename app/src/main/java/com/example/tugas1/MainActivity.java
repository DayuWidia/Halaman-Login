package com.example.tugas1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordTextView;
    private TextView createAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        createAccountTextView = findViewById(R.id.createAccountTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(username)) {
                    Toast.makeText(MainActivity.this, "Username harus berupa email dengan format yang benar (contoh: example@gmail.com)", Toast.LENGTH_SHORT).show();
                } else if (!isValidKataSandi(password)) {
                    Toast.makeText(MainActivity.this, "Password harus menggunakan huruf dan angka minimal 8 karakter", Toast.LENGTH_SHORT).show();
                } else {
                    // Tambahkan logika autentikasi di sini
                    // Misalnya, lakukan pengecekan kredensial atau permintaan ke server
                    Toast.makeText(MainActivity.this, "Masuk berhasil", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan logika untuk lupa kata sandi
                Toast.makeText(MainActivity.this, "Lupa Kata Sandi diklik", Toast.LENGTH_SHORT).show();
            }
        });

        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke halaman registrasi
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@gmail.com$");
    }

    private boolean isValidKataSandi(String kataSandi) {
        return kataSandi.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$");
    }
}
