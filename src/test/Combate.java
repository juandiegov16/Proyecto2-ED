/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 * Comprende los ataques posibles y calcula daño.
 * @author Juandi
 */
public class Combate {

    boolean LP = false; //Puño débil
    boolean MP = false; //Puño medio
    boolean HP = false; //Puño fuerte
    boolean LK = false; //Patada débil
    boolean MK = false; //Patada media
    boolean HK = false; //Patada fuerte
    boolean hadouken = false;
    int dmg = 0; //Daño

    void ataque() {
        this.dmg = this.LP ? 5 : (this.MP ? 8 : (this.HP ? 10 : (this.LK ? 5 : (this.MK ? 8 : (this.HK ? 12 : (this.hadouken ? 7 : 0))))));
    }

    void punoDebil() {
        if (!this.revisarFalso()) {
            this.LP = true;
        }
    }

    void punoMedio() {
        if (!this.revisarFalso()) {
            this.MP = true;
        }
    }

    void punoFuerte() {
        if (!this.revisarFalso()) {
            this.HP = true;
        }
    }

    void patadaDebil() {
        if (!this.revisarFalso()) {
            this.LK = true;
        }
    }

    void patadaMedia() {
        if (!this.revisarFalso()) {
            this.MK = true;
        }
    }

    void patadaFuerte() {
        if (!this.revisarFalso()) {
            this.HK = true;
        }
    }
    
    void hadouken() {
        if (!this.revisarFalso()) {
            this.hadouken = true;
        }
    }

    boolean revisarFalso() {
        return this.LP || this.MP || this.HP || this.LK || this.MK || this.HK|| this.hadouken;
    }
}
