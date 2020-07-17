package test;

import java.awt.image.BufferedImage;

/**
 * Contiene los atributos del personaje, coordenadas y arreglos BufferedImage[]
 * para las animaciones.
 * @author Juandi
 */
public abstract class EleccionPersonaje {

    protected int vida;
    protected int vidaMax;
    public int y;
    public int x = 50;
    protected int alturaSalto;
    protected int inicioAniY;
    protected BufferedImage[] aniInicio;
    protected BufferedImage[] aniPostura;
    protected BufferedImage[] moverDer;
    protected BufferedImage[] moverIzq;
    protected BufferedImage[] saltar;
    protected BufferedImage[] agacharse;
    protected BufferedImage[] saltoDer;
    protected BufferedImage[] saltoIzq;
    protected BufferedImage[] LP;
    protected BufferedImage[] MP;
    protected BufferedImage[] HP;
    protected BufferedImage[] LK;
    protected BufferedImage[] MK;
    protected BufferedImage[] HK;
    protected BufferedImage[] hadouken;
    protected BufferedImage[] hadoukenProy;
    protected BufferedImage[] bloqueo;
    protected BufferedImage[] golpeDebil;
    protected BufferedImage[] golpeMedio;
    protected BufferedImage[] golpeFuerte;
    protected BufferedImage[] victoria1;
    protected BufferedImage[] victoria2;
    protected int anchoAni;
    protected int alturaAni;
    protected int demoraInicioAni;
    protected int alturaPostura;
    protected int anchoPostura;
    protected int alturaDefault;
    protected int anchoDefault;
    protected int anchoMover;
    protected int alturaMover;
    protected int anchoAgacharse;
    protected int alturaAgacharse;
    protected int anchoAniSalto;
    protected int alturaAniSalto;
    protected int anchoSaltoDer;
    protected int alturaSaltoDer;
    protected int anchoSaltoIzq;
    protected int alturaSaltoIzq;
    protected int demoraLP;
    protected int demoraMP;
    protected int demoraHP;
    protected int demoraLK;
    protected int demoraMK;
    protected int demoraHK;
    protected int demoraHadouken;
    protected int demoraGolpe;

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
}
