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
 * Pantalla de pausa. Tendría opción de mostrar los controles.
 * Nota: Intentar implementar tras terminar todo.
 * @author Juandi
 */
public class PantallaPausa extends JFrame implements ActionListener {

    Timer tiempo;
    int opcion = 1;
    boolean escogido = false;

    PantallaPausa() {
        this.setSize(260, 250);
        this.setDefaultCloseOperation(3);
        this.setTitle("Proyecto 2 :: Pausado");
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(new TeclasPantallaPausa(this));
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
        bufferGraphics.drawString("Pausa", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("Pausa", bufferGraphics).getWidth() / 2, 60);
        bufferGraphics.fillRect(30, 70, 200, 5);
        bufferGraphics.setFont(new Font("SansSerif", 1, 20));
        if (this.opcion == 1) {
            bufferGraphics.setColor(Color.yellow.brighter());
        } else {
            bufferGraphics.setColor(Color.yellow.darker());
        }
        bufferGraphics.drawString("Resumir", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("Resumir", bufferGraphics).getWidth() / 2, 115);
        if (this.opcion == 2) {
            bufferGraphics.setColor(Color.yellow.brighter());
        } else {
            bufferGraphics.setColor(Color.yellow.darker());
        }
        bufferGraphics.drawString("-", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("-", bufferGraphics).getWidth() / 2, 145);
        if (this.opcion == 3) {
            bufferGraphics.setColor(Color.yellow.brighter());
        } else {
            bufferGraphics.setColor(Color.yellow.darker());
        }
        bufferGraphics.drawString("Ayuda", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("Ayuda", bufferGraphics).getWidth() / 2, 175);
        if (this.opcion == 4) {
            bufferGraphics.setColor(Color.yellow.brighter());
        } else {
            bufferGraphics.setColor(Color.yellow.darker());
        }
        bufferGraphics.drawString("Controles", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("Controles", bufferGraphics).getWidth() / 2, 205);
        if (this.opcion == 5) {
            bufferGraphics.setColor(Color.yellow.brighter());
        } else {
            bufferGraphics.setColor(Color.yellow.darker());
        }
        bufferGraphics.drawString("Salir del juego", this.getWidth() / 2 - (int) bufferGraphics.getFontMetrics().getStringBounds("Salir del juego", bufferGraphics).getWidth() / 2, 235);
        g.drawImage(bufferImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
        if (this.escogido && this.opcion != 4 && this.opcion != 3) {
            this.dispose();
        }
    }

    class TeclasPantallaPausa
            extends KeyAdapter {

        final /* synthetic */ PantallaPausa this$0;

        TeclasPantallaPausa(PantallaPausa pauseScreen) {
            this.this$0 = pauseScreen;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case 40:
                    this.this$0.opcion = this.this$0.opcion == 5 ? 1 : ++this.this$0.opcion;
                    break;
                case 38:
                    this.this$0.opcion = this.this$0.opcion == 1 ? 5 : --this.this$0.opcion;
                    break;
                case 32:
                    this.this$0.escogido = true;
                    break;
                case 27:
                    this.this$0.escogido = true;
                    this.this$0.opcion = 1;
                    break;
                default:
                    break;
            }
        }

    }
}
