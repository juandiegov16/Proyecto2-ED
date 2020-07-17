/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contenido del juego.
 * @author Juandi
 */
public class PanelLucha
        extends JPanel
        implements ActionListener {
    CPU com;
    Personaje jugador1;
    Personaje jugador2;
    Image fondo;
    Timer tiempo;
    long tiempoInicioJuego;
    long difTiempoJuego;
    int minsTiempoJuego = 10;
    int segTiempoJuego = 0;
    int cambioX1 = 0;
    int cambioX2 = 0;
    int anchoCuadro1 = 0;
    int anchoCuadro2 = 0;
    int alturaCuadro1 = 0;
    int alturaCuadro2 = 0;
    int anchoFondo;
    int alturaFondo;
    int opcionFin = 0;
    boolean juegoPausado = false;
    boolean juegoTerminado = false;
    Font razer;
    Rectangle rectAtaque1 = new Rectangle();
    Rectangle rectAtaque2 = new Rectangle();
    Rectangle rectPrueba = new Rectangle();
    boolean inicioAniTerminado = false;
    int cuadro1 = 0;
    int cuadro2 = 0;
    int color = 0;
    boolean pausa = false;
    boolean reinicio = false;
    long tiempoArranqueJuego;
    long tiempoInicioPelea = 0;
    long tiempoFin = 0;
    long tiempoColor = 0;
    long tiempoUltCuadro1 = 0;
    long tiempoUltCuadro2 = 0;
    boolean collided = false;

    PanelLucha(int ancho, int altura) throws IOException {
        this.jugador1 = new Personaje("ryu");
        this.jugador2 = new Personaje("ryu");
        this.com = new CPU();
        
        this.anchoFondo = ancho;
        this.alturaFondo = altura;
        this.jugador1.mover.maxX = this.anchoFondo;
        this.jugador2.mover.maxX = this.anchoFondo;
        this.jugador2.mover.x = this.jugador2.mover.maxX - 280 - this.jugador2.mover.x;
        this.jugador2.mover.dxstore = -1;
        this.jugador2.mover.dirJugador = -1;
        this.jugador2.userTiming = 1;
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("test/RAZEROBLIQUE.ttf");
        try {
            this.razer = Font.createFont(0, is);
        } catch (FontFormatException | IOException e) {
            System.out.println("No se puede leer");
            return;
        }
        this.addKeyListener(new AL(this));
        this.setFocusable(true);
        this.tiempo = new Timer(5, this);
        this.tiempoInicioJuego = System.currentTimeMillis();
    }

    public void iniciarJuego() {
        this.tiempo.start();
        this.tiempoArranqueJuego = System.currentTimeMillis();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.fondo, 0, 50, this.anchoFondo, this.alturaFondo, null);
        g2d.fillRect(0, 0, this.anchoFondo, 50);
        g2d.setColor(new Color(16777164));
        g2d.fill3DRect(2, 2, this.anchoFondo - 4, 45, true);
        g2d.setColor(Color.red.brighter());
        g2d.drawString("Jugador 1", 63, 14);
        g2d.setColor(Color.blue.brighter());
        g2d.drawString("Jugador 2", 463, 14);
        g2d.setColor(Color.black);
        g2d.fillRect(43, 19, 104, 14);
        g2d.setColor(new Color(6684672));
        g2d.fillRect(45, 21, 100, 10);
        int hpBar = this.jugador1.vida * 100 / this.jugador1.personaje.vidaMax;
        if (hpBar > 50) {
            g2d.setColor(new Color(6750003));
        } else if (hpBar <= 50 && hpBar > 20) {
            g2d.setColor(new Color(16750899));
        } else if (hpBar <= 20 && hpBar > 0) {
            g2d.setColor(new Color(16711680));
        }
        g2d.fillRect(45, 21, hpBar, 10);
        g2d.setColor(Color.black);
        g2d.drawString("HP: ", 20, 30);
        g2d.setColor(Color.black);
        g2d.fillRect(443, 19, 104, 14);
        g2d.setColor(new Color(6684672));
        g2d.fillRect(445, 21, 100, 10);
        int hpBar2 = this.jugador2.vida * 100 / this.jugador2.personaje.vidaMax;
        if (hpBar2 > 50) {
            g2d.setColor(new Color(6750003));
        } else if (hpBar2 <= 50 && hpBar2 > 20) {
            g2d.setColor(new Color(16750899));
        } else if (hpBar2 <= 20 && hpBar2 > 0) {
            g2d.setColor(new Color(16711680));
        }
        g2d.fillRect(445, 21, hpBar2, 10);
        g2d.setColor(Color.black);
        g2d.drawString("HP: ", 420, 30);
        if (!this.pausa) {
            g2d.drawImage(this.jugador1.personaje.aniInicio[0], this.jugador1.getX() + this.cambioX1 + this.jugador1.personaje.anchoAni / 2 - this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni / 2, this.jugador1.personaje.inicioAniY - this.jugador1.personaje.alturaAni, this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni, this.jugador1.personaje.alturaAni, this);
            g2d.drawImage(this.jugador2.personaje.aniInicio[0], this.jugador2.getX() + this.cambioX2 + this.jugador2.personaje.anchoAni / 2 - this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni / 2, this.jugador2.personaje.inicioAniY - this.jugador2.personaje.alturaAni, this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni, this.jugador2.personaje.alturaAni, this);
            if (System.currentTimeMillis() - this.tiempoArranqueJuego > 500) {
                this.pausa = true;
            }
        } else if (!this.inicioAniTerminado) {
            if (this.cuadro1 < this.jugador1.personaje.aniInicio.length - 2 || this.cuadro2 < this.jugador2.personaje.aniInicio.length - 2) {
                if (this.cuadro1 < this.jugador1.personaje.aniInicio.length - 1) {
                    g2d.drawImage(this.animarArreglo(this.jugador1.personaje.aniInicio, this.jugador1.demora, 1), this.jugador1.getX() + this.cambioX1 + this.jugador1.personaje.anchoAni / 2 - this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni / 2, this.jugador1.personaje.inicioAniY - this.jugador1.personaje.alturaAni, this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni, this.jugador1.personaje.alturaAni, this);
                } else {
                    g2d.drawImage(this.jugador1.personaje.aniInicio[this.jugador1.personaje.aniInicio.length - 1], this.jugador1.getX() + this.cambioX1 + this.jugador1.personaje.anchoAni / 2 - this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni / 2, this.jugador1.personaje.inicioAniY - this.jugador1.personaje.alturaAni, this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni, this.jugador1.personaje.alturaAni, this);
                }
                if (this.cuadro2 < this.jugador2.personaje.aniInicio.length - 1) {
                    g2d.drawImage(this.animarArreglo(this.jugador2.personaje.aniInicio, this.jugador2.demora, 2), this.jugador2.getX() + this.cambioX2 + this.jugador2.personaje.anchoAni / 2 - this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni / 2, this.jugador2.personaje.inicioAniY - this.jugador2.personaje.alturaAni, this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni, this.jugador2.personaje.alturaAni, this);
                } else {
                    g2d.drawImage(this.jugador2.personaje.aniInicio[this.jugador2.personaje.aniInicio.length - 1], this.jugador2.getX() + this.cambioX2 + this.jugador2.personaje.anchoAni / 2 - this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni / 2, this.jugador2.personaje.inicioAniY - this.jugador2.personaje.alturaAni, this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni, this.jugador2.personaje.alturaAni, this);
                }
            } else {
                g2d.setFont(this.razer.deriveFont(1, 73.0f));
                g2d.setColor(Color.black.brighter());
                g2d.drawString("Fight", this.getWidth() / 2 - (int) g2d.getFontMetrics().getStringBounds("Fight", g2d).getWidth() / 2, this.getHeight() / 2);
                g2d.setFont(this.razer.deriveFont(1, 70.0f));
                g2d.setColor(Color.GREEN.brighter());
                g2d.drawString("Fight", this.getWidth() / 2 - (int) g2d.getFontMetrics().getStringBounds("Fight", g2d).getWidth() / 2, this.getHeight() / 2);
                if (System.currentTimeMillis() - this.tiempoInicioPelea > 800) {
                    this.inicioAniTerminado = true;
                    this.cuadro1 = 0;
                    this.cuadro2 = 0;
                }
                g2d.drawImage(this.jugador1.personaje.aniInicio[this.jugador1.personaje.aniInicio.length - 1], this.jugador1.getX() + this.cambioX1 + this.jugador1.personaje.anchoAni / 2 - this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni / 2, this.jugador1.personaje.inicioAniY - this.jugador1.personaje.alturaAni, this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni, this.jugador1.personaje.alturaAni, this);
                g2d.drawImage(this.jugador2.personaje.aniInicio[this.jugador2.personaje.aniInicio.length - 1], this.jugador2.getX() + this.cambioX2 + this.jugador2.personaje.anchoAni / 2 - this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni / 2, this.jugador2.personaje.inicioAniY - this.jugador2.personaje.alturaAni, this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni, this.jugador2.personaje.alturaAni, this);
                try {
                    Thread.sleep(300);
                } catch (Exception var5_6) {
                    // empty catch block
                }
                this.tiempoInicioJuego = System.currentTimeMillis();
            }
        } else {
            this.difTiempoJuego = System.currentTimeMillis() - this.tiempoInicioJuego;
            long gameTimeLeft = 600000 - this.difTiempoJuego;
            g2d.drawString(String.format("%02d : %02d", gameTimeLeft / 60000, gameTimeLeft % 60000 / 1000), 300, 30);
            this.verificarColision();
            g2d.drawImage(this.animarArreglo(this.jugador1.getCharImageArray(), this.jugador1.demora, 1), this.jugador1.getX() + this.cambioX1 + this.jugador1.personaje.anchoAni / 2 - this.jugador1.mover.dirJugador * this.jugador1.personaje.anchoAni / 2, this.jugador1.getY() - this.alturaCuadro1, this.jugador1.mover.dirJugador * this.anchoCuadro1, this.alturaCuadro1, this);
            g2d.drawImage(this.animarArreglo(this.jugador2.getCharImageArray(), this.jugador2.demora, 2), this.jugador2.getX() + this.cambioX2 + this.jugador2.personaje.anchoAni / 2 - this.jugador2.mover.dirJugador * this.jugador2.personaje.anchoAni / 2, this.jugador2.getY() - this.alturaCuadro2, this.jugador2.mover.dirJugador * this.anchoCuadro2, this.alturaCuadro2, this);
            g2d.setColor(Color.RED);
            g2d.drawString("Jugador 1", this.jugador1.mover.x + 10, this.jugador1.getY() - this.alturaCuadro1 - 10);
            g2d.setColor(Color.BLUE);
            g2d.drawString("Jugador 2", this.jugador2.mover.x + 10, this.jugador2.getY() - this.alturaCuadro2 - 10);
            if (this.juegoTerminado) {
                g2d.setFont(this.razer.deriveFont(1, 35.0f));
                g2d.setColor(Color.GREEN);
                int playerWin = this.jugador1.noqueado ? 1 : 2;
                g2d.drawString("Player " + playerWin + " Wins!", this.getWidth() / 2 - (int) g2d.getFontMetrics().getStringBounds("Player " + playerWin + " Wins!", g2d).getWidth() / 2, this.getHeight() / 2);
                if (this.tiempoFin == 0) {
                    this.tiempoFin = System.currentTimeMillis();
                }
                if (this.reinicio) {
                    g2d.setFont(new Font("Serif", 1, 15));
                    if (System.currentTimeMillis() - this.tiempoColor > 100) {
                        this.color = this.color == 4 ? 1 : ++this.color;
                        this.tiempoColor = System.currentTimeMillis();
                    }
                    switch (this.color) {
                        case 1:
                            g2d.setColor(Color.yellow.brighter());
                            break;
                        case 2:
                            g2d.setColor(Color.orange.brighter());
                            break;
                        case 3:
                            g2d.setColor(Color.white.brighter());
                            break;
                        default:
                            g2d.setColor(Color.black.brighter());
                            break;
                    }
                    g2d.drawString("Press SPACE to continue", this.getWidth() / 2 - (int) g2d.getFontMetrics().getStringBounds("Press SPACE to continue", g2d).getWidth() / 2, this.getHeight() - 50);
                }
            } else if (this.juegoPausado) {
                g2d.setFont(this.razer.deriveFont(1, 30.0f));
                g2d.setColor(Color.yellow.brighter());
                g2d.drawString("Paused", this.getWidth() / 2 - (int) g2d.getFontMetrics().getStringBounds("Paused", g2d).getWidth() / 2, this.getHeight() / 2);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.juegoTerminado) {
            if (System.currentTimeMillis() - this.tiempoFin > 1000) {
                this.reinicio = true;
            }
        } else {
            this.jugador1.setearHitBox();
            this.jugador2.setearHitBox();
            this.verificarColision();
            this.revisarDireccion();
            this.jugador1.mover.mover();
            this.jugador2.mover.mover();
            Thread th = new Thread() {

            public synchronized void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                com.revisar(jugador1, jugador2);
                com.revisarAtaque(jugador1, jugador2);
                
            } catch (AWTException ex) {
                Logger.getLogger(PanelLucha.class.getName()).log(Level.SEVERE, null, ex);
            }
                }

            }

        };

        th.start();
        Thread y = new Thread();
        y.start();
            
            this.jugador2.noqueado = this.jugador1.revisarKO(2);
            this.jugador1.noqueado = this.jugador2.revisarKO(1);
            if (this.jugador2.noqueado || this.jugador1.noqueado) {
                this.jugador1.bloqueo = true;
                this.jugador2.bloqueo = true;
                this.juegoTerminado = true;
            }
        }
        this.repaint();
    }

    BufferedImage animarArreglo(BufferedImage[] arregloAni, int demora, int numJugador) {
        int cuadros = arregloAni.length - 1;
        if (numJugador == 1) {
            if (this.jugador1.nuevaImagen) {
                this.jugador2.victima = false;
                this.cuadro1 = 0;
                this.jugador1.mover.ataque.ataque();
            }
            if (System.currentTimeMillis() - this.tiempoUltCuadro1 > 50) {
                this.moverAtras(1);
            }
            if (arregloAni.equals(this.jugador1.personaje.saltoDer)) {
                this.movSalto(cuadros, 1, this.jugador1.mover.dirJugador);
            } else if (arregloAni.equals(this.jugador1.personaje.saltoIzq)) {
                this.movSalto(cuadros, 1, -this.jugador1.mover.dirJugador);
            }
            if (System.currentTimeMillis() - this.tiempoUltCuadro1 > (long) demora) {
                this.tiempoUltCuadro1 = System.currentTimeMillis();
                if (this.cuadro1 < cuadros) {
                    if (!arregloAni.equals(this.jugador1.personaje.saltoIzq) && !arregloAni.equals(this.jugador1.personaje.saltoDer)) {
                        ++this.cuadro1;
                    }
                    this.danoAtaque(1);
                    if (this.jugador1.personaje instanceof Ryu) {
                        if (arregloAni.equals(this.jugador1.personaje.LK)) {
                            this.cambioX1 = this.cuadro1 < 4 ? this.jugador1.mover.dirJugador * 15 : 0;
                        } else if (arregloAni.equals(this.jugador1.personaje.MK)) {
                            this.cambioX1 = this.cuadro1 == 1 ? (-this.jugador1.mover.dirJugador) * 20 : (this.cuadro1 == 2 ? (-this.jugador1.mover.dirJugador) * 30 : 0);
                        }
                    }
                } else {
                    this.cambioX1 = 0;
                    if (arregloAni.equals(this.jugador1.personaje.victoria1)) {
                        this.jugador1.victoria = true;
                    } else if (arregloAni.equals(this.jugador1.personaje.saltar)) {
                        if (this.jugador1.mover.y >= this.jugador1.mover.minAlturaSalto) {
                            this.jugador1.mover.saltoTerminado = false;
                            this.jugador1.mover.saltoMax = false;
                            this.jugador1.mover.y = this.jugador1.mover.minAlturaSalto;
                        } else {
                            this.jugador1.mover.moverAbajo();
                        }
                    } else if (!arregloAni.equals(this.jugador1.personaje.agacharse)) {
                        if (arregloAni.equals(this.jugador1.personaje.saltoDer)) {
                            this.movSalto(cuadros, 1, this.jugador1.mover.dirJugador);
                        } else if (arregloAni.equals(this.jugador1.personaje.saltoIzq)) {
                            this.movSalto(cuadros, 1, -this.jugador1.mover.dirJugador);
                        } else if (arregloAni.equals(this.jugador1.personaje.LP)) {
                            this.jugador1.mover.ataque.LP = false;
                        } else if (arregloAni.equals(this.jugador1.personaje.MP)) {
                            this.jugador1.mover.ataque.MP = false;
                        } else if (arregloAni.equals(this.jugador1.personaje.HP)) {
                            this.jugador1.mover.ataque.HP = false;
                        } else if (arregloAni.equals(this.jugador1.personaje.LK)) {
                            this.jugador1.mover.ataque.LK = false;
                        } else if (arregloAni.equals(this.jugador1.personaje.MK)) {
                            this.jugador1.mover.ataque.MK = false;
                        } else if (arregloAni.equals(this.jugador1.personaje.HK)) {
                            this.jugador1.mover.ataque.HK = false;
                        } else if (arregloAni.equals(this.jugador1.personaje.hadouken)){
                            this.jugador1.mover.ataque.hadouken = false;
                        } else if (arregloAni.equals(this.jugador1.personaje.bloqueo)) {
                            if (!this.jugador1.hit) {
                                this.jugador1.bloqueo = false;
                            }
                        } else {
                            this.cuadro1 = 0;
                        }
                    }
                }
            }
            if (arregloAni.equals(this.jugador1.personaje.saltar) && this.jugador1.mover.saltoTerminado) {
                if (this.jugador1.mover.y > this.jugador1.mover.maxAlturaSalto && !this.jugador1.mover.saltoMax) {
                    this.jugador1.mover.moverArriba();
                    if (this.cuadro1 < 3) {
                        ++this.cuadro1;
                    }
                } else {
                    this.jugador1.mover.saltoMax = true;
                    this.jugador1.mover.moverAbajo();
                    if (this.cuadro1 < cuadros) {
                        ++this.cuadro1;
                    }
                }
            }
            this.anchoCuadro1 = arregloAni[this.cuadro1].getWidth();
            this.alturaCuadro1 = arregloAni[this.cuadro1].getHeight();
            try {
                return arregloAni[this.cuadro1];
            } catch (Exception e) {
                return null;
            }
        }
        if (numJugador == 2) {
            if (this.jugador2.nuevaImagen) {
                this.jugador1.victima = false;
                this.cuadro2 = 0;
                this.jugador2.mover.ataque.ataque();
            }
            if (System.currentTimeMillis() - this.tiempoUltCuadro2 > 50) {
                this.moverAtras(2);
            }
            if (arregloAni.equals(this.jugador2.personaje.saltoDer)) {
                this.movSalto(cuadros, 2, this.jugador2.mover.dirJugador);
            } else if (arregloAni.equals(this.jugador2.personaje.saltoIzq)) {
                this.movSalto(cuadros, 2, -this.jugador2.mover.dirJugador);
            }
            if (System.currentTimeMillis() - this.tiempoUltCuadro2 > (long) demora) {
                this.tiempoUltCuadro2 = System.currentTimeMillis();
                if (this.cuadro2 < cuadros) {
                    if (!arregloAni.equals(this.jugador2.personaje.saltoIzq) && !arregloAni.equals(this.jugador2.personaje.saltoDer)) {
                        ++this.cuadro2;
                    }
                    this.danoAtaque(2);
                    if (arregloAni.equals(this.jugador2.personaje.LK)) {
                        this.cambioX2 = this.cuadro2 < 4 ? 15 : 0;
                    } else if (arregloAni.equals(this.jugador2.personaje.MK)) {
                        this.cambioX2 = this.cuadro2 == 1 ? (-this.jugador2.mover.dirJugador) * 20 : (this.cuadro2 == 2 ? (-this.jugador2.mover.dirJugador) * 30 : 0);
                    }
                } else {
                    this.cambioX2 = 0;
                    if (arregloAni.equals(this.jugador2.personaje.victoria1)) {
                        this.jugador2.victoria = true;
                    } else if (arregloAni.equals(this.jugador2.personaje.saltar)) {
                        if (this.jugador2.mover.y >= this.jugador2.mover.minAlturaSalto) {
                            this.jugador2.mover.saltoTerminado = false;
                            this.jugador2.mover.saltoMax = false;
                            this.jugador2.mover.y = this.jugador2.mover.minAlturaSalto;
                        } else {
                            this.jugador2.mover.moverAbajo();
                        }
                    } else if (!arregloAni.equals(this.jugador2.personaje.agacharse)) {
                        if (arregloAni.equals(this.jugador2.personaje.saltoDer)) {
                            this.movSalto(cuadros, 2, this.jugador2.mover.dirJugador);
                        } else if (arregloAni.equals(this.jugador2.personaje.saltoIzq)) {
                            this.movSalto(cuadros, 2, -this.jugador2.mover.dirJugador);
                        } else if (arregloAni.equals(this.jugador2.personaje.LP)) {
                            this.jugador2.mover.ataque.LP = false;
                        } else if (arregloAni.equals(this.jugador2.personaje.MP)) {
                            this.jugador2.mover.ataque.MP = false;
                        } else if (arregloAni.equals(this.jugador2.personaje.HP)) {
                            this.jugador2.mover.ataque.HP = false;
                        } else if (arregloAni.equals(this.jugador2.personaje.LK)) {
                            this.jugador2.mover.ataque.LK = false;
                        } else if (arregloAni.equals(this.jugador2.personaje.MK)) {
                            this.jugador2.mover.ataque.MK = false;
                        } else if (arregloAni.equals(this.jugador2.personaje.HK)) {
                            this.jugador2.mover.ataque.HK = false;
                        } else if (arregloAni.equals(this.jugador2.personaje.hadouken)){
                            this.jugador2.mover.ataque.hadouken = false;
                        } else if (arregloAni.equals(this.jugador2.personaje.bloqueo)) {
                            if (!this.jugador2.hit) {
                                this.jugador2.bloqueo = false;
                            }
                        } else {
                            this.cuadro2 = 0;
                        }
                    }
                }
            }
            if (arregloAni.equals(this.jugador2.personaje.saltar) && this.jugador2.mover.saltoTerminado) {
                if (this.jugador2.mover.y > this.jugador2.mover.maxAlturaSalto && !this.jugador2.mover.saltoMax) {
                    this.jugador2.mover.moverArriba();
                    if (this.cuadro2 < 3) {
                        ++this.cuadro2;
                    }
                } else {
                    this.jugador2.mover.saltoMax = true;
                    this.jugador2.mover.moverAbajo();
                    if (this.cuadro2 < cuadros) {
                        ++this.cuadro2;
                    }
                }
            }
            this.anchoCuadro2 = arregloAni[this.cuadro2].getWidth();
            this.alturaCuadro2 = arregloAni[this.cuadro2].getHeight();
            try {
                return arregloAni[this.cuadro2];
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    void movSalto(int numCuadros, int numJugador, int dx) {
        if (numJugador == 1) {
            if (!this.jugador1.mover.saltoMax) {
                if (this.jugador1.mover.y > this.jugador1.mover.maxAlturaSalto) {
                    this.jugador1.mover.moverArriba();
                } else {
                    this.jugador1.mover.saltoMax = true;
                }
                if (Math.abs(this.jugador1.mover.x - this.jugador1.mover.posInicialSalto) < 50) {
                    this.jugador1.mover.mover(dx);
                }
            } else {
                if (Math.abs(this.jugador1.mover.x - this.jugador1.mover.posInicialSalto) < 140) {
                    this.jugador1.mover.mover(dx);
                }
                if (this.cuadro1 < numCuadros - 1) {
                    if (System.currentTimeMillis() - this.jugador1.mover.ultTiempoCuadroSalto > this.jugador1.demora) {
                        ++this.cuadro1;
                        this.jugador1.mover.ultTiempoCuadroSalto = System.currentTimeMillis();
                    }
                } else if (this.jugador1.mover.y < this.jugador1.mover.minAlturaSalto) {
                    this.cuadro1 = numCuadros;
                    this.jugador1.mover.dx = 0;
                    this.jugador1.mover.moverAbajo();
                } else {
                    this.jugador1.mover.y = this.jugador1.mover.minAlturaSalto;
                    this.jugador1.mover.saltoDer = false;
                    this.jugador1.mover.saltoIzq = false;
                    this.jugador1.mover.saltoMax = false;
                }
            }
        } else if (numJugador == 2) {
            if (!this.jugador2.mover.saltoMax) {
                if (this.jugador2.mover.y > this.jugador2.mover.maxAlturaSalto) {
                    this.jugador2.mover.moverArriba();
                } else {
                    this.jugador2.mover.saltoMax = true;
                }
                if (Math.abs(this.jugador2.mover.x - this.jugador2.mover.posInicialSalto) < 50) {
                    this.jugador2.mover.mover(dx);
                }
            } else {
                if (Math.abs(this.jugador2.mover.x - this.jugador2.mover.posInicialSalto) < 140) {
                    this.jugador2.mover.mover(dx);
                }
                if (this.cuadro2 < 6) {
                    if (System.currentTimeMillis() - this.jugador2.mover.ultTiempoCuadroSalto > this.jugador2.demora) {
                        ++this.cuadro2;
                        this.jugador2.mover.ultTiempoCuadroSalto = System.currentTimeMillis();
                    }
                } else if (this.jugador2.mover.y < this.jugador2.mover.minAlturaSalto) {
                    this.cuadro2 = 7;
                    this.jugador2.mover.dx = 0;
                    this.jugador2.mover.moverAbajo();
                } else {
                    this.jugador2.mover.y = this.jugador2.mover.minAlturaSalto;
                    this.jugador2.mover.saltoDer = false;
                    this.jugador2.mover.saltoIzq = false;
                    this.jugador2.mover.saltoMax = false;
                }
            }
        }
    }

    void danoAtaque(int numJugador) {
        if (numJugador == 1) {
            if (this.jugador1.mover.ataque.LP) {
                this.jugador2.victima = true;
                if (this.cuadro1 == 1) {
                    if (this.jugador1.mover.dirJugador == 1) {
                        if (this.jugador1.personaje instanceof Ryu) {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + this.jugador1.personaje.anchoPostura + 13, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 20, 17, 15));
                        } else {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + 90, this.jugador1.mover.y - 56, 17, 15));
                        }
                    } else if (this.jugador1.personaje instanceof Ryu) {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 33, this.jugador1.mover.y - this.jugador2.personaje.alturaAni + 20, 17, 15));
                    } else {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 40, this.jugador1.mover.y - 53, 17, 15));
                    }
                }
            } else if (this.jugador1.mover.ataque.MP) {
                this.jugador2.victima = true;
                if (this.cuadro1 == 2) {
                    if (this.jugador1.mover.dirJugador == 1) {
                        if (this.jugador1.personaje instanceof Ryu) {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + this.jugador1.personaje.anchoAni + 18, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 20, 17, 15));
                        } else {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + 110, this.jugador1.mover.y - 65, 127, 15));
                        }
                    } else if (this.jugador1.personaje instanceof Ryu) {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 38, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 20, 17, 15));
                    } else {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 170, this.jugador1.mover.y - 65, 127, 15));
                    }
                }
            } else if (this.jugador1.mover.ataque.HP) {
                this.jugador2.victima = true;
                if (this.cuadro1 == 1) {
                    if (this.jugador1.mover.dirJugador == 1) {
                        if (this.jugador1.personaje instanceof Ryu) {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + this.jugador1.personaje.anchoAni + 13, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 22, 17, 15));
                        } else {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + 110, this.jugador1.mover.y - 57, 160, 15));
                        }
                    } else if (this.jugador1.personaje instanceof Ryu) {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 33, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 20, 17, 15));
                    } else {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 203, this.jugador1.mover.y - 57, 160, 15));
                    }
                }
            } else if (this.jugador1.mover.ataque.LK) {
                this.jugador2.victima = true;
                if (this.cuadro1 == 1) {
                    if (this.jugador1.mover.dirJugador == 1) {
                        if (this.jugador1.personaje instanceof Ryu) {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + this.jugador1.personaje.anchoAni + 28, this.jugador1.mover.y - 28, 17, 15));
                        } else {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + 185, this.jugador1.mover.y - 27, 27, 15));
                        }
                    } else if (this.jugador1.personaje instanceof Ryu) {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 48, this.jugador1.mover.y - 28, 17, 15));
                    } else {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 146, this.jugador1.mover.y - 27, 27, 15));
                    }
                }
            } else if (this.jugador1.mover.ataque.MK) {
                this.jugador2.victima = true;
                if (this.cuadro1 == 2) {
                    if (this.jugador1.mover.dirJugador == 1) {
                        if (this.jugador1.personaje instanceof Ryu) {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + this.jugador1.personaje.anchoAni, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 15, 17, 15));
                        } else {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + 200, this.jugador1.mover.y - 100, 35, 15));
                        }
                    } else if (this.jugador1.personaje instanceof Ryu) {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 20, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 15, 17, 15));
                    } else {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 165, this.jugador1.mover.y - 100, 35, 15));
                    }
                }
            } else if (this.jugador1.mover.ataque.HK) {
                this.jugador2.victima = true;
                if (this.cuadro1 == 3) {
                    if (this.jugador1.mover.dirJugador == 1) {
                        if (this.jugador1.personaje instanceof Ryu) {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + this.jugador1.personaje.anchoAni + 20, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 7, 20, 18));
                        } else {
                            this.damage(2, new Rectangle(this.jugador1.mover.x + 190, this.jugador1.mover.y - 115, 35, 25));
                        }
                    } else if (this.jugador1.personaje instanceof Ryu) {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 40, this.jugador1.mover.y - this.jugador1.personaje.alturaAni + 7, 20, 18));
                    } else {
                        this.damage(2, new Rectangle(this.jugador1.mover.x - 155, this.jugador1.mover.y - 115, 35, 25));
                    }
                }
            }
        } else if (numJugador == 2) {
            if (this.jugador2.mover.ataque.LP) {
                this.jugador1.victima = true;
                if (this.cuadro2 == 1) {
                    if (this.jugador2.mover.dirJugador == 1) {
                        if (this.jugador2.personaje instanceof Ryu) {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + this.jugador2.personaje.anchoPostura + 13, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 20, 17, 15));
                        } else {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + 90, this.jugador2.mover.y - 56, 17, 15));
                        }
                    } else if (this.jugador2.personaje instanceof Ryu) {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 33, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 20, 17, 15));
                    } else {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 40, this.jugador2.mover.y - 53, 17, 15));
                    }
                }
            } else if (this.jugador2.mover.ataque.MP) {
                this.jugador1.victima = true;
                if (this.cuadro2 == 2) {
                    if (this.jugador2.mover.dirJugador == 1) {
                        if (this.jugador2.personaje instanceof Ryu) {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + this.jugador2.personaje.anchoAni + 18, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 20, 17, 15));
                        } else {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + 110, this.jugador2.mover.y - 65, 127, 15));
                        }
                    } else if (this.jugador2.personaje instanceof Ryu) {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 38, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 20, 17, 15));
                    } else {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 170, this.jugador2.mover.y - 65, 127, 15));
                    }
                }
            } else if (this.jugador2.mover.ataque.HP) {
                this.jugador1.victima = true;
                if (this.cuadro2 == 1) {
                    if (this.jugador2.mover.dirJugador == 1) {
                        if (this.jugador2.personaje instanceof Ryu) {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + this.jugador2.personaje.anchoAni + 13, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 22, 17, 15));
                        } else {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + 110, this.jugador2.mover.y - 57, 160, 15));
                        }
                    } else if (this.jugador2.personaje instanceof Ryu) {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 33, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 20, 17, 15));
                    } else {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 203, this.jugador2.mover.y - 57, 160, 15));
                    }
                }
            } else if (this.jugador2.mover.ataque.LK) {
                this.jugador1.victima = true;
                if (this.cuadro2 == 1) {
                    if (this.jugador2.mover.dirJugador == 1) {
                        if (this.jugador2.personaje instanceof Ryu) {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + this.jugador2.personaje.anchoAni + 28, this.jugador2.mover.y - 28, 17, 15));
                        } else {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + 185, this.jugador2.mover.y - 27, 27, 15));
                        }
                    } else if (this.jugador2.personaje instanceof Ryu) {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 48, this.jugador2.mover.y - 28, 17, 15));
                    } else {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 146, this.jugador2.mover.y - 27, 27, 15));
                    }
                }
            } else if (this.jugador2.mover.ataque.MK) {
                this.jugador1.victima = true;
                if (this.cuadro2 == 2) {
                    if (this.jugador2.mover.dirJugador == 1) {
                        if (this.jugador2.personaje instanceof Ryu) {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + this.jugador2.personaje.anchoAni, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 15, 17, 15));
                        } else {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + 200, this.jugador2.mover.y - 100, 35, 15));
                        }
                    } else if (this.jugador2.personaje instanceof Ryu) {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 20, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 15, 17, 15));
                    } else {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 165, this.jugador2.mover.y - 100, 35, 15));
                    }
                }
            } else if (this.jugador2.mover.ataque.HK) {
                this.jugador1.victima = true;
                if (this.cuadro2 == 3) {
                    if (this.jugador2.mover.dirJugador == 1) {
                        if (this.jugador2.personaje instanceof Ryu) {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + this.jugador2.personaje.anchoAni + 20, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 7, 20, 18));
                        } else {
                            this.damage(1, new Rectangle(this.jugador2.mover.x + 190, this.jugador2.mover.y - 115, 35, 25));
                        }
                    } else if (this.jugador2.personaje instanceof Ryu) {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 40, this.jugador2.mover.y - this.jugador2.personaje.alturaAni + 7, 20, 18));
                    } else {
                        this.damage(1, new Rectangle(this.jugador2.mover.x - 155, this.jugador2.mover.y - 115, 35, 25));
                    }
                }
            }
        }
    }

    void damage(int numJugador, Rectangle atkBox) {
        if (numJugador == 2) {
            this.jugador2.setearHitBox();
            this.rectAtaque1 = atkBox;
            if (this.jugador2.flechaIzqPresionada && !this.jugador2.flechaDerPresionada) {
                this.jugador2.flechaIzqPresionada();
            } else if (this.jugador2.flechaDerPresionada && !this.jugador2.flechaIzqPresionada) {
                this.jugador2.flechaDerPresionada();
            }
            if (this.jugador2.hitBox.intersects(atkBox)) {
                this.jugador2.hit = true;
                if (!this.jugador2.bloqueo) {
                    this.jugador2.vida -= this.jugador1.mover.ataque.dmg;
                }
            }
            if (this.jugador2.mover.x <= 0 || this.jugador2.mover.x >= this.jugador2.mover.maxX - this.jugador2.mover.anchoAniJugador) {
                this.jugador1.mover.setearUltimaPos();
            } else {
                this.jugador2.mover.setearUltimaPos();
            }
        } else if (numJugador == 1) {
            this.jugador1.setearHitBox();
            this.rectAtaque2 = atkBox;
            if (this.jugador1.flechaIzqPresionada && !this.jugador1.flechaDerPresionada) {
                this.jugador1.flechaIzqPresionada();
            } else if (this.jugador1.flechaDerPresionada && !this.jugador1.flechaIzqPresionada) {
                this.jugador1.flechaDerPresionada();
            }
            if (this.jugador1.hitBox.intersects(atkBox)) {
                this.jugador1.hit = true;
                if (!this.jugador1.bloqueo) {
                    this.jugador1.vida -= this.jugador2.mover.ataque.dmg;
                }
                if (this.jugador1.mover.x <= 0 || this.jugador1.mover.x >= this.jugador1.mover.maxX - this.jugador1.mover.anchoAniJugador) {
                    this.jugador2.mover.setearUltimaPos();
                } else {
                    this.jugador1.mover.setearUltimaPos();
                }
            }
        }
    }

    void moverAtras(int userNo) {
        if (userNo == 1) {
            if (this.jugador2.hit) {
                this.jugador2.hit = this.jugador2.mover.x <= 0 || this.jugador2.mover.x >= this.jugador2.mover.maxX - this.jugador2.mover.anchoAniJugador ? this.jugador1.mover.golpeMover(this.jugador1.mover.nivelDmg, true) : this.jugador2.mover.golpeMover(this.jugador1.mover.nivelDmg, false);
            }
        } else if (userNo == 2 && this.jugador1.hit) {
            this.jugador1.hit = this.jugador1.mover.x <= 0 || this.jugador1.mover.x >= this.jugador1.mover.maxX - this.jugador1.mover.anchoAniJugador ? this.jugador2.mover.golpeMover(this.jugador2.mover.nivelDmg, true) : this.jugador1.mover.golpeMover(this.jugador2.mover.nivelDmg, false);
        }
    }

    void revisarDireccion() {
        double user1Location = this.jugador1.hitBox.getCenterX();
        double user2Location = this.jugador2.hitBox.getCenterX();
        if (!this.jugador1.revisarSaltoFalso() && !this.jugador2.revisarSaltoFalso()) {
            if (user1Location < user2Location && this.jugador1.mover.dirJugador == -1) {
                this.jugador1.mover.dirJugador = 1;
                this.jugador2.mover.dirJugador = -1;
            } else if (user1Location > user2Location && this.jugador1.mover.dirJugador == 1) {
                this.jugador1.mover.dirJugador = -1;
                this.jugador2.mover.dirJugador = 1;
            }
            if (this.jugador1.flechaIzqPresionada) {
                this.jugador1.flechaIzqPresionada();
            } else if (this.jugador1.flechaDerPresionada) {
                this.jugador1.flechaDerPresionada();
            }
            if (this.jugador2.flechaIzqPresionada) {
                this.jugador2.flechaIzqPresionada();
            } else if (this.jugador2.flechaDerPresionada) {
                this.jugador2.flechaDerPresionada();
            }
        }
    }

    void verificarColision() {
        this.jugador1.revisarBordes();
        this.jugador2.revisarBordes();
        if (this.jugador1.hitBox.intersects(this.jugador2.hitBox)) {
            if (this.jugador1.mover.dirJugador == 1) {
                int loc1 = (int) this.jugador1.hitBox.getMaxX();
                int loc2 = (int) this.jugador2.hitBox.getMinX();
                int diff = (loc1 - loc2) / 2;
                if (loc1 > loc2 && !this.jugador1.revisarSaltoFalso() && !this.jugador2.revisarSaltoFalso()) {
                    this.revisarDireccion();
                    this.jugador1.mover.suelo();
                    this.jugador2.mover.suelo();
                    this.jugador1.mover.x -= diff;
                    this.jugador2.mover.x += diff;
                    if (this.jugador1.flechaIzqPresionada) {
                        this.jugador1.mover.moverIzquierda();
                    }
                    if (this.jugador2.flechaDerPresionada) {
                        this.jugador2.mover.moverDerecha();
                    }
                } else {
                    this.jugador1.mover.parar = 1;
                    this.jugador2.mover.parar = 1;
                }
            } else if (this.jugador1.mover.dirJugador == -1) {
                int loc1 = (int) this.jugador1.hitBox.getMinX();
                int loc2 = (int) this.jugador2.hitBox.getMaxX();
                int diff = (loc2 - loc1) / 2;
                if (loc1 < loc2 && !this.jugador1.revisarSaltoFalso() && !this.jugador2.revisarSaltoFalso()) {
                    this.revisarDireccion();
                    this.jugador1.mover.x += diff;
                    this.jugador2.mover.x -= diff;
                    if (this.jugador1.flechaDerPresionada) {
                        this.jugador1.mover.moverDerecha();
                    }
                    if (this.jugador2.flechaIzqPresionada) {
                        this.jugador2.mover.moverIzquierda();
                    }
                } else {
                    this.jugador1.mover.parar = 1;
                    this.jugador2.mover.parar = 1;
                }
            }
        } else {
            this.jugador1.mover.parar = 1;
            this.jugador2.mover.parar = 1;
        }
    }

    void resumir() {
        this.juegoPausado = false;
        this.tiempo.start();
    }
}

class AL extends KeyAdapter {

    final PanelLucha this$0;

    AL(PanelLucha actionPanel) {
        this.this$0 = actionPanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.this$0.inicioAniTerminado) {
            int key = e.getKeyCode();
            if (this.this$0.reinicio) {
                if (key == 32) {
                    this.this$0.opcionFin = 1;
                }
            } else {
                if (key == 65) {
                    this.this$0.jugador1.flechaIzqPresionada();
                }
                if (key == 68) {
                    this.this$0.jugador1.flechaDerPresionada();
                }
                if (key == 87) {
                    this.this$0.jugador1.flechaArrPresionada();
                }
                if (key == 83) {
                    this.this$0.jugador1.flechaAbPresionada();
                }
                if (key == 67) {
                    this.this$0.jugador1.punoDebilPresionado();
                }
                if (key == 86) {
                    this.this$0.jugador1.punoMedioPresionado();
                }
                if (key == 66) {
                    this.this$0.jugador1.punoFuertePresionado();
                }
                if (key == 70) {
                    this.this$0.jugador1.patadaDebilPresionada();
                }
                if (key == 71) {
                    this.this$0.jugador1.patadaMediaPresionada();
                }
                if (key == 72) {
                    this.this$0.jugador1.patadaFuertePresionada();
                }
                if (key == 84) {
                    this.this$0.jugador1.hadoukenPresionada();
                }
                if (key == 37) {
                    this.this$0.jugador2.flechaIzqPresionada();
                }
                if (key == 39) {
                    this.this$0.jugador2.flechaDerPresionada();
                }
                if (key == 38) {
                    this.this$0.jugador2.flechaArrPresionada();
                }
                if (key == 40) {
                    this.this$0.jugador2.flechaAbPresionada();
                }
                if (key == 97) {
                    this.this$0.jugador2.punoDebilPresionado();
                }
                if (key == 98) {
                    this.this$0.jugador2.punoMedioPresionado();
                }
                if (key == 99) {
                    this.this$0.jugador2.punoFuertePresionado();
                }
                if (key == 100) {
                    this.this$0.jugador2.patadaDebilPresionada();
                }
                if (key == 101) {
                    this.this$0.jugador2.patadaMediaPresionada();
                }
                if (key == 102) {
                    this.this$0.jugador2.patadaFuertePresionada();
                }
                if (key == 104) {
                    this.this$0.jugador2.hadoukenPresionada();
                }
                if (key == 27 && this.this$0.inicioAniTerminado && !this.this$0.juegoPausado) {
                    this.this$0.tiempo.stop();
                    this.this$0.juegoPausado = true;
                    this.this$0.repaint();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.this$0.inicioAniTerminado) {
            int key = e.getKeyCode();
            if (key == 65) {
                this.this$0.jugador1.flechaIzqSoltada();
            }
            if (key == 68) {
                this.this$0.jugador1.flechaDerSoltada();
            }
            if (key == 87) {
                this.this$0.jugador1.flechaArrSoltada();
            }
            if (key == 83) {
                this.this$0.jugador1.flechaAbSoltada();
            }
            if (key == 67) {
                this.this$0.jugador1.punoDebilSoltado();
            }
            if (key == 86) {
                this.this$0.jugador1.punoMedioSoltado();
            }
            if (key == 66) {
                this.this$0.jugador1.punoFuerteSoltado();
            }
            if (key == 70) {
                this.this$0.jugador1.patadaDebilSoltada();
            }
            if (key == 71) {
                this.this$0.jugador1.patadaMediaSoltada();
            }
            if (key == 72) {
                this.this$0.jugador1.patadaFuerteSoltada();
            }
            if (key == 84) {
                this.this$0.jugador1.hadoukenSoltada();
            }
            if (key == 37) {
                this.this$0.jugador2.flechaIzqSoltada();
            }
            if (key == 39) {
                this.this$0.jugador2.flechaDerSoltada();
            }
            if (key == 38) {
                this.this$0.jugador2.flechaArrSoltada();
            }
            if (key == 40) {
                this.this$0.jugador2.flechaAbSoltada();
            }
            if (key == 97) {
                this.this$0.jugador2.punoDebilSoltado();
            }
            if (key == 98) {
                this.this$0.jugador2.punoMedioSoltado();
            }
            if (key == 99) {
                this.this$0.jugador2.punoFuerteSoltado();
            }
            if (key == 100) {
                this.this$0.jugador2.patadaDebilSoltada();
            }
            if (key == 101) {
                this.this$0.jugador2.patadaMediaSoltada();
            }
            if (key == 102) {
                this.this$0.jugador2.patadaFuerteSoltada();
            }
            if (key == 104) {
                this.this$0.jugador2.hadoukenSoltada();
            }
        }
    }
}
