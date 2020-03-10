package com.example.tcpclientavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etIP;
    private EditText etPuerto;
    private Button btnConectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIP = findViewById(R.id.etIP);
        etPuerto = findViewById(R.id.etPuerto);
        btnConectar = findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra("IP", etIP.getText().toString());
                    intent.putExtra("Puerto", etPuerto.getText().toString());
                    startActivity(intent);
                }
        );

    }

}
