package com.example.pgpb_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.os.Bundle;

import com.example.pgpb_12.databinding.ActivityRegisterBinding;
public class RegisterActivty extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        prefManager = PrefManager.getInstance(this);
        binding.btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = binding.edtUsername.getText().toString();
                String password = binding.edtPassword.getText().toString();
                String confirmPassword = binding.edtPasswordConfirm.getText().toString();

                if (username.isEmpty() || password.isEmpty()|| confirmPassword.isEmpty()){
                    Toast.makeText(RegisterActivty.this, "Mohon Isi Semua Data",
                            Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivty.this, "Password Tidak Sama",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    prefManager.saveUsername(username);
                    prefManager.savePassword(password);
                    prefManager.setLoggedIn(true);
                    checkLoginStatus();
                }
            }
        });
    }
    private void checkLoginStatus(){
        boolean isLoggedIn = prefManager.isLoggedIn();;;
        if (isLoggedIn) {
            Toast.makeText(RegisterActivty.this,
                    "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivty.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(RegisterActivty.this, "Registrasi gagal", Toast.LENGTH_SHORT).show();
        }
    }
}