package com.example.tcpclientavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String ip = getIntent().getExtras().getString("IP");
        String puertoString = getIntent().getExtras().getString("Puerto");
        int puerto = Integer.parseInt(puertoString);

        ComunicacionTCP comm = new ComunicacionTCP(this);
        comm.solicitarConexion(ip, puerto);

    }
}
