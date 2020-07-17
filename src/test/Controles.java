/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Pantalla extra que tendría información de los controles.
 * Nota: Implementar después de haber terminado la parte obligatoria.
 * @author Juandi
 */
public class Controles extends JFrame
        implements ActionListener {

    Timer tiempo;
    boolean terminado = false;

    Controles() {
        this.setSize(560, 500);
        this.setDefaultCloseOperation(3);
        this.setTitle("Proyecto 2 :: Controls");
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(new controlsKeys(this));
        this.setBackground(Color.black);
        this.tiempo = new Timer(5, this);
        this.tiempo.start();
    }

    @Override
    public void paint(Graphics g) {
        Image bufferImage = this.createImage(this.getSize().width, this.getSize().height);
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.setFont(new Font("DialogInput", 1, 40));
        bufferGraphics.setColor(Color.yellow.brighter());
        bufferGraphics.drawString("Controles", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("Controls", bufferGraphics).getWidth() / 2, 60);
        bufferGraphics.fillRect(30, 70, 500, 5);
        bufferGraphics.setFont(new Font("DialogInput", 1, 26));
        bufferGraphics.drawString("Saltar", 20, 160);
        bufferGraphics.drawString("Agacharse", 20, 190);
        bufferGraphics.drawString("Mover izq", 20, 220);
        bufferGraphics.drawString("Mover der", 20, 250);
        bufferGraphics.drawString("Puño débil", 20, 280);
        bufferGraphics.drawString("Puño medio", 20, 310);
        bufferGraphics.drawString("Puño fuerte", 20, 340);
        bufferGraphics.drawString("Patada débil", 20, 370);
        bufferGraphics.drawString("Patada media", 20, 400);
        bufferGraphics.drawString("Patada fuerte", 20, 430);
        bufferGraphics.drawString("Hadouken",20,0);
        bufferGraphics.setColor(Color.red.brighter());
        bufferGraphics.drawString("Jugador 1", 240, 120);
        bufferGraphics.drawString("W", 290, 160);
        bufferGraphics.drawString("S", 290, 190);
        bufferGraphics.drawString("A", 290, 220);
        bufferGraphics.drawString("D", 290, 250);
        bufferGraphics.drawString("C", 290, 280);
        bufferGraphics.drawString("V", 290, 310);
        bufferGraphics.drawString("B", 290, 340);
        bufferGraphics.drawString("F", 290, 370);
        bufferGraphics.drawString("G", 290, 400);
        bufferGraphics.drawString("H", 290, 430);
        bufferGraphics.drawString("T",290,460);
        bufferGraphics.setColor(Color.blue.brighter());
        bufferGraphics.drawString("Jugador 2", 400, 120);
        bufferGraphics.drawString("ARRIBA", 450, 160);
        bufferGraphics.drawString("IZQUIERDA", 450, 190);
        bufferGraphics.drawString("DERECHA", 450, 220);
        bufferGraphics.drawString("ABAJO", 450, 250);
        bufferGraphics.drawString("NUMPAD1", 410, 280);
        bufferGraphics.drawString("NUMPAD2", 410, 310);
        bufferGraphics.drawString("NUMPAD3", 410, 340);
        bufferGraphics.drawString("NUMPAD4", 410, 370);
        bufferGraphics.drawString("NUMPAD5", 410, 400);
        bufferGraphics.drawString("NUMPAD6", 410, 430);
        bufferGraphics.drawString("NUMPAD8", 410, 460);
        bufferGraphics.setColor(Color.white.brighter());
        bufferGraphics.drawString("Presione ESPACIO para salir", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("Press SPACE to exit", bufferGraphics).getWidth() / 2, 480);
        g.drawImage(bufferImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
        if (this.terminado) {
            this.dispose();
        }
    }
}

class controlsKeys extends KeyAdapter {

    final /* synthetic */ Controles this$0;

    controlsKeys(Controles controls) {
        this.this$0 = controls;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 32) {
            this.this$0.terminado = true;
        }
    }

}
