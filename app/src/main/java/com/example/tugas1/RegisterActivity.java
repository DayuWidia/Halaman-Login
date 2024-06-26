package com.example.tugas1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText namaEditText;
    private EditText emailEditText;
    private EditText kataSandiEditText;
    private EditText nomorTelpEditText;
    private CheckBox termsCheckBox;
    private Button daftarButton;
    private TextView alreadyHaveAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi view
        namaEditText = findViewById(R.id.namaEditText);
        emailEditText = findViewById(R.id.emailEditText);
        kataSandiEditText = findViewById(R.id.kataSandiEditText);
        nomorTelpEditText = findViewById(R.id.nomorTeleponEditText);
        termsCheckBox = findViewById(R.id.termsCheckBox);
        daftarButton = findViewById(R.id.registerButton);
        alreadyHaveAccountTextView = findViewById(R.id.alreadyHaveAccountTextView);

        // Set listener untuk tombol daftar
        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil nilai dari input
                String nama = namaEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String kataSandi = kataSandiEditText.getText().toString();
                String nomorTelp = nomorTelpEditText.getText().toString();

                // Validasi input menggunakan metode yang sudah Anda definisikan
                if (!isValidNama(nama)) {
                    namaEditText.setError("Nama harus menggunakan huruf");
                    return;
                }

                if (!isValidEmail(email)) {
                    emailEditText.setError("Email harus menggunakan format yang benar (contoh: example@gmail.com)");
                    return;
                }

                if (!isValidKataSandi(kataSandi)) {
                    kataSandiEditText.setError("Kata sandi harus menggunakan huruf dan angka minimal 8 karakter");
                    return;
                }

                if (!isValidNomorTelp(nomorTelp)) {
                    nomorTelpEditText.setError("Nomor telepon harus menggunakan 12 angka");
                    return;
                }

                if (!termsCheckBox.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "Harap setujui Syarat & Ketentuan", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Jika semua input valid, maka lanjutkan ke proses registrasi
                // Misalnya, Anda bisa menambahkan logika untuk menyimpan data ke database atau melakukan proses registrasi lainnya
                // Contoh sederhana menggunakan Toast
                Toast.makeText(RegisterActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();

                // Setelah registrasi berhasil, pindah ke halaman lain atau lakukan tindakan lanjutan
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Menutup activity saat ini agar tidak bisa kembali lagi dengan tombol back
            }
        });

        // Set listener untuk teks "Sudah memiliki akun?"
        alreadyHaveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke halaman login
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Metode validasi
    private boolean isValidNama(String nama) {
        return nama.matches("[a-zA-Z]+");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@gmail.com$");
    }

    private boolean isValidKataSandi(String kataSandi) {
        return kataSandi.matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$");
    }

    private boolean isValidNomorTelp(String nomorTelp) {
        return nomorTelp.matches("^[0-9]{12}$");
    }

    // Method untuk menampilkan dialog syarat dan ketentuan
    public void showTermsDialog(View view) {
        // Buat dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Syarat & Ketentuan");
        builder.setMessage("Dengan menggunakan aplikasi ini, Anda setuju untuk tidak menggunakan informasi yang diperoleh dari aplikasi ini untuk tujuan yang melanggar hukum atau merugikan pihak lain. Anda bertanggung jawab penuh atas data yang Anda masukkan dan mengonfirmasi bahwa informasi yang Anda berikan adalah benar dan akurat.");

        // Tambahkan tombol OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tindakan setelah tombol OK ditekan (jika perlu)
            }
        });

        // Tampilkan dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
