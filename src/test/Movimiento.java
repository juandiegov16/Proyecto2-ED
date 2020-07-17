/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 * Contiene las funciones para el movimiento de los personajes.
 * @author Juandi
 */
public class Movimiento {

    int x = 10;
    int y = 160;
    int dx;
    int dy;
    int dxstore = 1;
    int maxAlturaSalto = 50;
    int minAlturaSalto = 160;
    int posInicialSalto;
    int dirJugador = 1;
    private int ultimaPos;
    int anchoAniJugador = 0;
    int alturaAniJugador = 0;
    int parar = 1;
    int maxX;
    long ultTiempoCuadroSalto = 0;
    long ultTiempoCuadroCaminar = 0;
    double rapidezX = 2.0;
    double rapidezY = 4.0;
    int demoraX = 10;
    boolean saltoTerminado;
    boolean saltoMax;
    boolean saltoDer = false;
    boolean saltoIzq = false;
    int nivelDmg = 0; //Nivel daño
    int nivelGolpe = 0;
    Thread animacionSalto;
    Thread animacionPuno; //Animación puño
    Combate ataque = new Combate();

    void mover() {
        if (!this.ataque.revisarFalso()) {
            this.revisarParedes();
            if (this.dx != 0) {
                this.caminar(this.dx, this.dirJugador, this.rapidezX, this.demoraX);
            }
        }
    }

    void caminar(int dx, int dir, double rapidez, int demoraTiempo) {
        if (System.currentTimeMillis() - this.ultTiempoCuadroCaminar > demoraTiempo) {
            this.x = (int) (this.x + (dx * dir) * rapidez);
            this.ultTiempoCuadroCaminar = System.currentTimeMillis();
        }
    }

    void setearUltimaPos() {
        this.ultimaPos = this.x;
    }

    void empezarSalto() {
        this.posInicialSalto = this.x;
        this.ultTiempoCuadroSalto = System.currentTimeMillis();
    }

    boolean golpeMover(int nivelGolpe, boolean agresor) {
        if (!agresor) {
            this.nivelGolpe = nivelGolpe;
        }
        if (nivelGolpe == 1) {
            if (Math.abs(this.ultimaPos - this.x) > 30) {
                return false;
            }
            this.x -= this.dirJugador * 2;
            return true;
        }
        if (nivelGolpe == 2) {
            if (Math.abs(this.ultimaPos - this.x) > 40) {
                return false;
            }
            this.x -= this.dirJugador * 2;
            return true;
        }
        if (nivelGolpe == 3) {
            if (Math.abs(this.ultimaPos - this.x) > 50) {
                return false;
            }
            this.x -= this.dirJugador * 2;
            return true;
        }
        return false;
    }

    void mover(int dir) {
        if (dir == -1 && this.x <= 0) {
            dir = 0;
            this.x = 0;
        }
        if (dir == 1 && this.x + this.anchoAniJugador >= this.maxX) {
            dir = 0;
            this.x = this.maxX - this.anchoAniJugador;
        }
        this.caminar(dir, 1, this.rapidezX, this.demoraX);
    }

    void moverIzquierda() {
        this.dx = -1 * this.dirJugador;
    }

    void moverDerecha() {
        this.dx = 1 * this.dirJugador;
    }

    void moverArriba() {
        if (this.y <= this.maxAlturaSalto) {
            this.y = this.maxAlturaSalto;
        } else if (System.currentTimeMillis() - this.ultTiempoCuadroSalto > 10) {
            this.y = (int) (this.y - this.rapidezY);
            this.ultTiempoCuadroSalto = System.currentTimeMillis();
        }
    }

    void moverAbajo() {
        if (this.y >= this.minAlturaSalto) {
            this.y = this.minAlturaSalto;
        } else if (System.currentTimeMillis() - this.ultTiempoCuadroSalto > 10) {
            this.y = (int) (this.y + this.rapidezY);
            this.ultTiempoCuadroSalto = System.currentTimeMillis();
        }
    }

    void revisarParedes() {
        if (this.dx == -1 * this.dirJugador && this.x <= 0) {
            this.dx = 0;
            this.x = 0;
        }
        if (this.dx == 1 * this.dirJugador && this.x + this.anchoAniJugador >= this.maxX) {
            this.dx = 0;
            this.x = this.maxX - this.anchoAniJugador;
        }
        if (this.dx == 0) {
            if (this.x <= 0) {
                this.x = 0;
            } else if (this.x + this.anchoAniJugador >= this.maxX) {
                this.x = this.maxX - this.anchoAniJugador;
            }
        }
    }

    void agacharse() {
        this.dx = 0;
    }

    void suelo() {
        this.y = this.minAlturaSalto;
    }
}
