package com.example.tcpclientavanzado;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ComunicacionTCP extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String ip;
    private int puerto;

    private AppCompatActivity app;

    private OnMessageListener observer;

    public void setObserver(OnMessageListener observer) {
        this.observer = observer;
    }

    //Usar setters para sacar del null a las interfaces
    public ComunicacionTCP(AppCompatActivity app) {
        this.app = app;
    }

    // Hilo de recepcion
    @Override
    public void run() {
        try {
            this.socket = new Socket(this.ip, this.puerto);

            // Reader
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            this.reader = new BufferedReader(isr);

            // Writer
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            this.writer = new BufferedWriter(osw);

            while (true) {
                recibirMensaje();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Solicitar conexion
    public void solicitarConexion(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
        this.start();
    }

    // Mandar un mensaje
    public void mandarMensaje(String mensaje) {
        new Thread(

                () -> {
                    try {
                        writer.write(mensaje + "\n");
                        writer.flush();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

        ).start();

    }

    // Recibir mensaje
    public void recibirMensaje() throws IOException {
        String line = reader.readLine();
        //System.out.println("<<<" + line);
        observer.onMessage(line);

    }

    // Cerrar conexion
    public void cerrarConexion() {
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Interfaz = implementar un tipo de dato
    public interface OnMessageListener {
        void onMessage(String mensaje);
    }

}
