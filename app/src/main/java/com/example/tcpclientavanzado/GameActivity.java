package com.example.tcpclientavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements ComunicacionTCP.OnMessageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String ip = getIntent().getExtras().getString("IP");
        String puertoString = getIntent().getExtras().getString("Puerto");
        int puerto = Integer.parseInt(puertoString);

        ComunicacionTCP comm = new ComunicacionTCP(this);
        comm.setObserver(this);
        comm.solicitarConexion(ip, puerto);

    }

    @Override
    public void onMessage(String mensaje) {
        if (mensaje.equals("NO")) {
            runOnUiThread(
                    () -> {
                        Toast.makeText(this, "El correo esta MAL", Toast.LENGTH_SHORT).show();
                    }
            );
        }
        /*if (line.equals("SI")) {
            Intent i = new Intent(app, MainActivity.class);
            app.startActivity(i);
        }*/
    }
}
