/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Contiene banderas de estado del personaje, la hitbox y otros atributos de
 * importancia.
 * @author Juandi
 */
public class Personaje {
    Movimiento mover;
    EleccionPersonaje personaje;
    Image pose;
    int vida;
    int demora;
    int userTiming = 0;
    int anchoHitBox = 70;
    int alturaHitBox = 100;
    int hitBoxY = 165;
    Rectangle hitBox;
    BufferedImage[] imgGuardada = null;
    boolean nuevaImagen = false;
    boolean hit = false;
    boolean bloqueo = false;
    boolean victima = false;
    boolean noqueado = false;
    boolean victoria = false;
    boolean barraEspacioActiva = false;
    boolean flechaDerPresionada = false;
    boolean flechaIzqPresionada = false;
    boolean flechaAbPresionada = false;
    boolean LPpresionado = false;
    boolean MPpresionado = false;
    boolean HPpresionado = false;
    boolean LKpresionado = false;
    boolean MKpresionado = false;
    boolean HKpresionado = false;
    boolean hadoukenpresionado = false;
    boolean proyectilpresionado = false;

    Personaje(String obtenerPersonaje) throws IOException {
        if (obtenerPersonaje.equals("ryu")) {
            this.personaje = new Ryu();
        }
        this.mover = new Movimiento();
        this.vida = this.personaje.vida;
        this.mover.y = this.personaje.getY();
        this.mover.x = this.personaje.getX();
        this.mover.minAlturaSalto = this.personaje.inicioAniY;
        this.mover.maxAlturaSalto = this.personaje.alturaSalto + this.personaje.alturaAni;
        this.mover.anchoAniJugador = this.personaje.anchoAni;
        this.mover.alturaAniJugador = this.personaje.alturaAni;
        this.demora = this.personaje.demoraInicioAni;
        this.setearHitBox();
    }

    BufferedImage[] getCharImageArray() {
        this.setearAlturaHitBox();
        this.setearAnchoHitBox();
        this.mover.revisarParedes();
        this.revisarNivelDmg();
        if (this.noqueado) {
            if (!this.victoria) {
                this.revisarNueva(this.personaje.victoria1);
                this.imgGuardada = this.personaje.victoria1;
                this.demora = 130;
                return this.personaje.victoria1;
            }
            this.revisarNueva(this.personaje.victoria2);
            this.imgGuardada = this.personaje.victoria2;
            this.demora = 130;
            return this.personaje.victoria2;
        }
        if (this.mover.saltoTerminado) {
            this.personaje.alturaAni = this.personaje.alturaAniSalto;
            this.personaje.anchoAni = this.personaje.anchoAniSalto;
            this.revisarNueva(this.personaje.saltar);
            this.imgGuardada = this.personaje.saltar;
            this.demora = 85;
            return this.personaje.saltar;
        }
        if (this.hit && !this.bloqueo) {
            this.revisarNueva(this.personaje.golpeDebil);
            this.imgGuardada = this.personaje.golpeDebil;
            this.demora = this.personaje.demoraGolpe;
            return this.personaje.golpeDebil;
        }
        this.personaje.alturaAni = this.personaje.alturaDefault;
        this.personaje.anchoAni = this.personaje.anchoDefault;
        if (this.bloqueo) {
            this.revisarNueva(this.personaje.bloqueo);
            this.imgGuardada = this.personaje.bloqueo;
            this.demora = 155;
            return this.personaje.bloqueo;
        }
        if (this.mover.ataque.LP) {
            this.revisarNueva(this.personaje.LP);
            this.imgGuardada = this.personaje.LP;
            this.demora = this.personaje.demoraLP;
            return this.personaje.LP;
        }
        if (this.mover.ataque.MP) {
            this.revisarNueva(this.personaje.MP);
            this.imgGuardada = this.personaje.MP;
            this.demora = this.personaje.demoraMP;
            return this.personaje.MP;
        }
        if (this.mover.ataque.HP) {
            this.revisarNueva(this.personaje.HP);
            this.imgGuardada = this.personaje.HP;
            this.demora = this.personaje.demoraHP;
            return this.personaje.HP;
        }
        if (this.mover.ataque.LK) {
            this.revisarNueva(this.personaje.LK);
            this.imgGuardada = this.personaje.LK;
            this.demora = this.personaje.demoraLK;
            return this.personaje.LK;
        }
        if (this.mover.ataque.MK) {
            this.revisarNueva(this.personaje.MK);
            this.imgGuardada = this.personaje.MK;
            this.demora = this.personaje.demoraMK;
            return this.personaje.MK;
        }
        if (this.mover.ataque.HK) {
            this.revisarNueva(this.personaje.HK);
            this.imgGuardada = this.personaje.HK;
            this.demora = this.personaje.demoraHK;
            return this.personaje.HK;
        }
        if (this.mover.ataque.hadouken) {
            this.revisarNueva(this.personaje.hadouken);
            this.imgGuardada = this.personaje.hadouken;
            this.demora = this.personaje.demoraHadouken;
            return this.personaje.hadouken;
        }
        if (this.flechaAbPresionada) {
            this.personaje.alturaAni = this.personaje.alturaAgacharse;
            this.personaje.anchoAni = this.personaje.anchoAgacharse;
            this.revisarNueva(this.personaje.agacharse);
            this.imgGuardada = this.personaje.agacharse;
            this.demora = 140;
            return this.personaje.agacharse;
        }
        if (this.mover.saltoDer) {
            this.personaje.alturaAni = this.personaje.alturaSaltoDer;
            this.personaje.anchoAni = this.personaje.anchoSaltoDer;
            this.mover.dx = 0;
            this.revisarNueva(this.personaje.saltoDer);
            this.imgGuardada = this.personaje.saltoDer;
            this.demora = 55;
            return this.personaje.saltoDer;
        }
        if (this.mover.saltoIzq) {
            this.personaje.alturaAni = this.personaje.alturaSaltoIzq;
            this.personaje.anchoAni = this.personaje.anchoSaltoIzq;
            this.revisarNueva(this.personaje.saltoIzq);
            this.imgGuardada = this.personaje.saltoIzq;
            this.demora = 55;
            return this.personaje.saltoIzq;
        }
        if (this.flechaDerPresionada) {
            this.personaje.alturaAni = this.personaje.alturaMover;
            this.personaje.anchoAni = this.personaje.anchoMover;
            if (this.mover.dirJugador == 1) {
                this.revisarNueva(this.personaje.moverDer);
                this.imgGuardada = this.personaje.moverDer;
                this.demora = 95;
                return this.personaje.moverDer;
            }
            this.revisarNueva(this.personaje.moverIzq);
            this.imgGuardada = this.personaje.moverIzq;
            this.demora = 95;
            return this.personaje.moverIzq;
        }
        if (this.flechaIzqPresionada) {
            this.personaje.alturaAni = this.personaje.alturaMover;
            this.personaje.anchoAni = this.personaje.anchoMover;
            if (this.mover.dirJugador == -1) {
                this.revisarNueva(this.personaje.moverDer);
                this.imgGuardada = this.personaje.moverDer;
                this.demora = 95;
                return this.personaje.moverDer;
            }
            this.revisarNueva(this.personaje.moverIzq);
            this.imgGuardada = this.personaje.moverIzq;
            this.demora = 95;
            return this.personaje.moverIzq;
        }
        this.revisarNueva(this.personaje.aniPostura);
        this.imgGuardada = this.personaje.aniPostura;
        this.demora = 85;
        this.personaje.alturaAni = this.personaje.alturaPostura;
        this.personaje.anchoAni = this.personaje.anchoPostura;
        return this.personaje.aniPostura;
    }

    void revisarBordes() {
        if (this.mover.x < 0) {
            this.mover.x = 0;
        } else if (this.mover.x > 530) {
            this.mover.x = 530;
        }
    }

    void revisarNueva(BufferedImage[] imgActual) {
        this.nuevaImagen = this.imgGuardada != imgActual;
    }

    void flechaIzqPresionada() {
        if (!this.revisarSaltoFalso() && !this.flechaAbPresionada) {
            if (this.flechaDerPresionada) {
                this.mover.dx = 0;
                this.flechaDerPresionada = false;
            } else if (this.mover.dirJugador == 1 && this.victima) {
                this.bloqueo = true;
                this.mover.dx = 0;
            } else {
                this.mover.moverIzquierda();
                this.flechaIzqPresionada = true;
            }
        }
    }

    void flechaDerPresionada() {
        if (!this.flechaAbPresionada && !this.revisarSaltoFalso()) {
            if (this.flechaIzqPresionada) {
                this.mover.dx = 0;
                this.flechaIzqPresionada = false;
            } else if (this.mover.dirJugador == -1 && this.victima) {
                this.bloqueo = true;
                this.mover.dx = 0;
            } else {
                this.mover.moverDerecha();
                this.flechaDerPresionada = true;
            }
        }
    }

    void flechaArrPresionada() {
        if (!this.flechaAbPresionada && !this.revisarSaltoFalso()) {
            switch (this.mover.dx) {
                case 0:
                    this.mover.saltoTerminado = true;
                    break;
                case 1:
                    this.mover.saltoDer = true;
                    this.mover.empezarSalto();
                    this.mover.dx = 0;
                    break;
                case -1:
                    this.mover.saltoIzq = true;
                    this.mover.empezarSalto();
                    this.mover.dx = 0;
                    break;
                default:
                    break;
            }
        }
    }

    void flechaAbPresionada() {
        if (!this.flechaAbPresionada && !this.revisarSaltoFalso()) {
            this.flechaAbPresionada = true;
            this.mover.agacharse();
        }
    }

    void punoDebilPresionado() {
        if (!this.revisarAtaqueFalso() && !this.revisarSaltoFalso()) {
            this.mover.ataque.punoDebil();
        }
        this.LPpresionado = true;
    }

    void punoMedioPresionado() {
        if (!this.revisarAtaqueFalso() && !this.revisarSaltoFalso()) {
            this.mover.ataque.punoMedio();
        }
        this.MPpresionado = true;
    }

    void punoFuertePresionado() {
        if (!this.revisarAtaqueFalso() && !this.revisarSaltoFalso()) {
            this.mover.ataque.punoFuerte();
        }
        this.HPpresionado = true;
    }

    void patadaDebilPresionada() {
        if (!this.revisarAtaqueFalso() && !this.revisarSaltoFalso()) {
            this.mover.ataque.patadaDebil();
        }
        this.LKpresionado = true;
    }

    void patadaMediaPresionada() {
        if (!this.revisarAtaqueFalso() && !this.revisarSaltoFalso()) {
            this.mover.ataque.patadaMedia();
        }
        this.MKpresionado = true;
    }

    void patadaFuertePresionada() {
        if (!this.revisarAtaqueFalso() && !this.revisarSaltoFalso()) {
            this.mover.ataque.patadaFuerte();
        }
        this.HKpresionado = true;
    }
    
    void hadoukenPresionada() {
        if (!this.revisarAtaqueFalso() && !this.revisarSaltoFalso()) {
            this.mover.ataque.hadouken();
        }
        this.hadoukenpresionado = true;
    }

    void revisarStatus() {
        System.out.println("move.x: " + this.mover.x + "  y: " + this.mover.y + "  dx: " + this.mover.dx + "  dxstore: " + this.mover.dxstore + "  hp: " + this.vida + "/" + this.personaje.vidaMax + "  userDir: " + this.mover.dirJugador + "  HK: " + this.mover.ataque.HK);
    }

    void salir() {
        System.exit(0);
    }

    void flechaIzqSoltada() {
        this.flechaIzqPresionada = false;
        this.mover.dx = 0;
        this.bloqueo = false;
    }

    void flechaDerSoltada() {
        this.flechaDerPresionada = false;
        this.mover.dx = 0;
        this.bloqueo = false;
    }

    void punoDebilSoltado() {
        this.LPpresionado = false;
    }

    void punoMedioSoltado() {
        this.MPpresionado = false;
    }

    void punoFuerteSoltado() {
        this.HPpresionado = false;
    }

    void patadaDebilSoltada() {
        this.LKpresionado = false;
    }

    void patadaMediaSoltada() {
        this.MKpresionado = false;
    }

    void patadaFuerteSoltada() {
        this.HKpresionado = false;
    }

    void hadoukenSoltada() {
        this.hadoukenpresionado = false;
    }
    
    void flechaArrSoltada() {
        if (this.flechaDerPresionada) {
            this.mover.dx = 1;
        } else if (this.flechaIzqPresionada) {
            this.mover.dx = -1;
        }
    }

    void flechaAbSoltada() {
        if (this.flechaAbPresionada) {
            this.flechaAbPresionada = false;
            this.bloqueo = false;
        }
    }

    boolean revisarFalso() {
        return this.flechaDerPresionada || this.flechaIzqPresionada || this.flechaAbPresionada || this.LPpresionado || this.mover.saltoTerminado || this.mover.saltoIzq || this.mover.saltoDer || this.mover.ataque.revisarFalso();
    }

    boolean revisarSaltoFalso() {
        return this.mover.saltoTerminado || this.mover.saltoIzq || this.mover.saltoDer;
    }

    boolean revisarAtaqueFalso() {
        return this.LPpresionado || this.MPpresionado || this.HPpresionado || this.LKpresionado || this.MKpresionado || this.HKpresionado || this.hadoukenpresionado || this.flechaAbPresionada;
    }

    boolean revisarKO(int jugadorPerdio) {
        return this.vida <= 0;
    }

    void setearHitBox() {
        this.setearAnchoHitBox();
        this.setearAlturaHitBox();
        this.setearHitBoxY();
        this.hitBox = new Rectangle(this.mover.x + 7 + (- this.mover.dirJugador) * 5, this.hitBoxY, this.anchoHitBox, this.alturaHitBox);
        this.mover.alturaAniJugador = this.personaje.alturaAni;
        this.mover.anchoAniJugador = this.personaje.anchoAni;
    }

    void setearAnchoHitBox() {
        this.anchoHitBox = this.personaje.anchoAni - 20;
    }

    void setearAlturaHitBox() {
        this.alturaHitBox = this.flechaAbPresionada ? 66 : this.personaje.alturaAni;
        if (this.mover.y < 160) {
            this.alturaHitBox = 50;
        }
    }

    void setearHitBoxY() {
        this.hitBoxY = this.mover.y - this.alturaHitBox;
    }

    void revisarNivelDmg() {
        this.mover.nivelDmg = this.mover.ataque.LP || this.mover.ataque.LK ? 1 : (this.mover.ataque.MP || this.mover.ataque.MK ? 2 : (this.mover.ataque.HP || this.mover.ataque.HK ? 3 : 0));
    }

    int getX() {
        return this.mover.x;
    }

    int getY() {
        return this.mover.y;
    }

    int getVida() {
        return this.vida;
    }
}
