/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Container;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * Maneja la ventana del juego e inicializa su contenido.
 * @author Juandi
 */
public class Juego extends JFrame {

    PanelLucha panelLucha;

    Juego(int ancho, int altura) throws IOException {
        //this.add(new Fondo());
        this.setDefaultCloseOperation(3);
        this.setSize(ancho + 5, altura + 70);
        this.setVisible(false);
        this.setTitle("Proyecto 2");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Container containPanels = this.getContentPane();
        containPanels.setLayout(new BoxLayout(containPanels, 3));
        this.panelLucha = new PanelLucha(ancho, altura);
        containPanels.add(this.panelLucha);
        //this.panelLucha.fondo = escenario;

    }

}
