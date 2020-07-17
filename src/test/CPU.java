/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author Juandi
 */
class CPU {

    ArbolBehavior arbolMOV, arbolATK;

    CPU() {
        arbolMOV = new ArbolBehavior(new NodoBehavior("Moviendo?"));
        arbolMOV.getRaiz().setDer(new NodoBehavior("Derecha?"));
        arbolMOV.getRaiz().setIzq(new NodoBehavior("Mover Izquierda", KeyEvent.VK_LEFT));
        arbolMOV.getRaiz().getDer().setDer(new NodoBehavior("Mover Derecha", KeyEvent.VK_RIGHT));
        arbolMOV.getRaiz().getDer().setIzq(new NodoBehavior("Mover Izquierda", KeyEvent.VK_LEFT));

        arbolATK = new ArbolBehavior(new NodoBehavior("Ataque"));
        arbolATK.getRaiz().setDer(new NodoBehavior("Patada", KeyEvent.VK_NUMPAD5));
        arbolATK.getRaiz().setIzq(new NodoBehavior("Puño", KeyEvent.VK_NUMPAD2));
    }

    public void revisarAtaque(Personaje jugador1, Personaje jugador2) throws AWTException {
        Robot r = new Robot();
        Random rand = new Random();
        int value = rand.nextInt(2);
        int accion;
        int resta;
        resta = Math.abs(jugador1.mover.x - jugador2.mover.x);

        if (resta <= 70) {
            if (value == 0) {
                accion = arbolATK.getRaiz().getDer().getAction();
                r.delay(50);
                r.keyPress(accion);
                
                r.keyRelease(accion);
            } else {
               accion = arbolATK.getRaiz().getIzq().getAction();
                r.delay(50);
                r.keyPress(accion);
                r.keyRelease(accion);
            }
        }else{
            //System.out.println(resta);        
        }

    }

    public void revisar(Personaje jugador1, Personaje jugador2) throws AWTException {
        Robot r = new Robot();
        int accion;
        if (jugador1.flechaDerPresionada == true || jugador1.flechaIzqPresionada == true) {
            if (jugador1.flechaDerPresionada) {
                accion = arbolMOV.getRaiz().getDer().getDer().getAction();
                
                r.keyPress(accion);
                r.delay(50);
                r.keyRelease(accion);
                
                revisarAtaque(jugador1, jugador2);                
            }else {
                accion = arbolMOV.getRaiz().getDer().getIzq().getAction();
            
                r.keyPress(accion);
                r.delay(50);
                r.keyRelease(accion);
                   
            revisarAtaque(jugador1, jugador2);

        }
        }else {
                accion = arbolMOV.getRaiz().getDer().getIzq().getAction();
            
                r.keyPress(accion);
                r.delay(50);
                r.keyRelease(accion);
                   
            revisarAtaque(jugador1, jugador2);

        } 
        Random rand = new Random();
        int value = rand.nextInt(2);
    }

    public void crearArbol() {
        arbolMOV = new ArbolBehavior(new NodoBehavior("Inicio"));
        arbolMOV.getRaiz().setDer(new NodoBehavior("Izq o Der"));
        arbolMOV.getRaiz().setIzq(new NodoBehavior("Mover Der"));
        arbolMOV.getRaiz().getDer().setDer(new NodoBehavior("Mover Izq"));
        arbolMOV.getRaiz().getDer().setIzq(new NodoBehavior("Mover Der"));
    }
}
