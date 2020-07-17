/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;

/**
 * Clase principal.
 * @author Juandi
 */
public class Main implements ActionListener {

    static Juego juego;
    static Thread t1;
    static Timer tiempo;
    static long tiempoInicio;
    static boolean verificar;
    static boolean panelLuchaAbierto;
    static boolean terminado;
    static boolean pantallaPausaAbierta;
    static PantallaPausa pausa;
    static Controles control;
    static Image fondo;

    static {
        verificar = false;
        panelLuchaAbierto = false;
        terminado = false;
        pantallaPausaAbierta = false;
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Main();
    }

    Main() throws IOException {
        juego = new Juego(700, 300);
        tiempo = new Timer(5, this);
        tiempo.start();
        tiempoInicio = System.currentTimeMillis();
    }

    void start() throws IOException {
        if (!panelLuchaAbierto) {
            this.cargarJuego();
        }
    }

    void cargarJuego() {
        verificar = true;
        Frame r1 = new Frame();
        t1 = new Thread(r1);
        t1.start();
        panelLuchaAbierto = true;
        juego.setVisible(true);
        Main.juego.panelLucha.iniciarJuego();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!panelLuchaAbierto && !terminado) {
            try {
                this.start();
            } catch (Exception ex) {
                //bloque catch vacío
            }
        }
        if (panelLuchaAbierto) {
            if (Main.juego.panelLucha.juegoTerminado) {
                if (Main.juego.panelLucha.opcionFin == 1) {
                    juego.dispose();
                    verificar = false;
                    panelLuchaAbierto = false;
                    terminado = false;
                    pantallaPausaAbierta = false;
                }
            } else {
                if (Main.juego.panelLucha.juegoPausado && !pantallaPausaAbierta) {
                    pantallaPausaAbierta = true;
                    pausa = new PantallaPausa();
                }
                if (pantallaPausaAbierta && Main.pausa.escogido) {
                    pantallaPausaAbierta = false;
                    Main.juego.panelLucha.juegoPausado = false;
                    switch (Main.pausa.opcion) {
                        case 1:
                            Main.juego.panelLucha.resumir();
                            break;
                        case 2:
                            juego.dispose();
                            verificar = false;
                            panelLuchaAbierto = false;
                            terminado = false;
                            break;
                        case 3:
                            Main.pausa.escogido = false;
                            break;
                        case 4:
                            Main.pausa.escogido = false;
                            pantallaPausaAbierta = true;
                            control = new Controles();
                            break;
                        case 5:
                            System.exit(0);
                        default:
                            break;
                    }

                }

            }
        }
    }

    class Frame implements Runnable {

        Frame() {
        }

        @Override
        public void run() {
            try {
                Main.juego = new Juego(700, 300);
            } catch (IOException ex) {
            }
        }
    }
}
