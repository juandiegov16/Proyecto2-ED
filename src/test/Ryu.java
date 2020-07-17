package test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Personaje a utilizar por ambos jugadores. 
 * @author Juandi
 */
public class Ryu
        extends EleccionPersonaje {

    private BufferedImage[] ryuStart = new BufferedImage[12]; //ani. inicio
    private BufferedImage[] ryuStance = new BufferedImage[5]; //pose estática
    private BufferedImage[] ryuMoveR = new BufferedImage[6]; //Mover derecha
    private BufferedImage[] ryuMoveL = new BufferedImage[6]; //Mover izquierda
    private BufferedImage[] ryuJump = new BufferedImage[7]; //Salto
    private BufferedImage[] ryuCrouch = new BufferedImage[2]; //Agachado
    private BufferedImage[] ryuJumpR = new BufferedImage[8]; //Salto der
    private BufferedImage[] ryuJumpL = new BufferedImage[8]; //Salto izq
    private BufferedImage[] ryuLP = new BufferedImage[2]; //Puño débil
    private BufferedImage[] ryuMP = new BufferedImage[3]; //Puño medio
    private BufferedImage[] ryuHP = new BufferedImage[5]; //Puño fuerte
    private BufferedImage[] ryuLK = new BufferedImage[5]; //Patada débil
    private BufferedImage[] ryuMK = new BufferedImage[4]; //Patada media
    private BufferedImage[] ryuHK = new BufferedImage[6]; //patada fuerte
    private BufferedImage[] ryuBlock = new BufferedImage[2]; //BLOQUEO!
    private BufferedImage[] ryuHitL = new BufferedImage[2]; //golpeado
    private BufferedImage[] ryuWin1 = new BufferedImage[5]; //ani. victoria
    private BufferedImage[] ryuWin2 = new BufferedImage[6]; //ani. victoria 2
    private BufferedImage[] ryuFireball = new BufferedImage[5]; //pose Hadouken
    private BufferedImage[] fireball = new BufferedImage[8]; //bolafuego

    Ryu() throws IOException {
        this.createImageArrays();
        this.vida = 200;
        this.vidaMax = 200;
        this.inicioAniY = 260;
        this.y = 260;
        this.alturaSalto = 50;
        this.demoraInicioAni = 100;
        this.aniInicio = this.ryuStart;
        this.aniPostura = this.ryuStance;
        this.moverDer = this.ryuMoveR;
        this.moverIzq = this.ryuMoveL;
        this.saltar = this.ryuJump;
        this.agacharse = this.ryuCrouch;
        this.saltoDer = this.ryuJumpR;
        this.saltoIzq = this.ryuJumpL;
        this.LP = this.ryuLP;
        this.MP = this.ryuMP;
        this.HP = this.ryuHP;
        this.LK = this.ryuLK;
        this.MK = this.ryuMK;
        this.HK = this.ryuHK;
        this.hadouken=this.ryuFireball;
        this.hadoukenProy=this.fireball;
        this.bloqueo = this.ryuBlock;
        this.golpeDebil = this.ryuHitL;
        this.victoria1 = this.ryuWin1;
        this.victoria2 = this.ryuWin2;
    }

    void createImageArrays() throws IOException {
        BufferedImage ryuSpriteSheet = ImageIO.read(this.getClass().getClassLoader().getResource("test/Ryu.gif"));
        int width = 71;
        int height = 104;
        int h_width = 75;
        int h_height = 37;
        this.anchoAni = this.anchoDefault = width;
        this.anchoMover = this.anchoDefault;
        this.anchoPostura = this.anchoDefault;
        this.anchoSaltoDer = this.anchoDefault;
        this.anchoSaltoIzq = this.anchoDefault;
        this.alturaAni = this.alturaDefault = height;
        this.alturaMover = this.alturaDefault;
        this.alturaPostura = this.alturaDefault;
        this.alturaSaltoDer = this.alturaDefault;
        this.alturaSaltoIzq = this.alturaDefault;
        this.ryuStart[0] = ryuSpriteSheet.getSubimage(17, 107 - height, width, height);
        this.ryuStart[1] = ryuSpriteSheet.getSubimage(17, 107 - height, width, height);
        this.ryuStart[2] = ryuSpriteSheet.getSubimage(91, 107 - height, width, height);
        this.ryuStart[3] = ryuSpriteSheet.getSubimage(166, 107 - height, width, height);
        this.ryuStart[4] = ryuSpriteSheet.getSubimage(245, 107 - height, width, height);
        this.ryuStart[5] = ryuSpriteSheet.getSubimage(321, 107 - height, width, height);
        this.ryuStart[6] = ryuSpriteSheet.getSubimage(394, 107 - height, width, height);
        this.ryuStart[7] = ryuSpriteSheet.getSubimage(465, 107 - height, width, height);
        this.ryuStart[8] = ryuSpriteSheet.getSubimage(535, 108 - height, width, height);
        this.ryuStart[9] = ryuSpriteSheet.getSubimage(37, 221 - height, width, height);
        this.ryuStart[10] = ryuSpriteSheet.getSubimage(37, 221 - height, width, height);
        this.ryuStart[11] = ryuSpriteSheet.getSubimage(110, 221 - height, width, height);
        this.alturaPostura = 104;
        this.anchoPostura = 71;
        this.ryuStance[0] = ryuSpriteSheet.getSubimage(189, 221 - height, width, height);
        this.ryuStance[1] = ryuSpriteSheet.getSubimage(268, 221 - height, width, height);
        this.ryuStance[2] = ryuSpriteSheet.getSubimage(349, 221 - height, width, height);
        this.ryuStance[3] = ryuSpriteSheet.getSubimage(426, 221 - height, width, height);
        this.ryuStance[4] = ryuSpriteSheet.getSubimage(505, 221 - height, width, height);
        this.ryuMoveR[0] = ryuSpriteSheet.getSubimage(63, 331 - height, width, height);
        this.ryuMoveR[1] = ryuSpriteSheet.getSubimage(144, 331 - height, width, height);
        this.ryuMoveR[2] = ryuSpriteSheet.getSubimage(227, 331 - height, width, height);
        this.ryuMoveR[3] = ryuSpriteSheet.getSubimage(304, 331 - height, width, height);
        this.ryuMoveR[4] = ryuSpriteSheet.getSubimage(378, 331 - height, width, height);
        this.ryuMoveR[5] = ryuSpriteSheet.getSubimage(454, 331 - height, width, height);
        this.ryuMoveL[0] = ryuSpriteSheet.getSubimage(65, 441 - height, width, height);
        this.ryuMoveL[1] = ryuSpriteSheet.getSubimage(142, 441 - height, width, height);
        this.ryuMoveL[2] = ryuSpriteSheet.getSubimage(220, 441 - height, width, height);
        this.ryuMoveL[3] = ryuSpriteSheet.getSubimage(291, 441 - height, width, height);
        this.ryuMoveL[4] = ryuSpriteSheet.getSubimage(368, 441 - height, width, height);
        this.ryuMoveL[5] = ryuSpriteSheet.getSubimage(455, 441 - height, width, height);
        this.alturaAniSalto = 108;
        this.anchoAniSalto = 67;
        this.ryuJump[0] = ryuSpriteSheet.getSubimage(114, 568 - this.alturaAniSalto, this.anchoAniSalto, this.alturaAniSalto);
        this.ryuJump[1] = ryuSpriteSheet.getSubimage(192, 568 - this.alturaAniSalto, this.anchoAniSalto, this.alturaAniSalto);
        this.ryuJump[2] = ryuSpriteSheet.getSubimage(273, 568 - this.alturaAniSalto, this.anchoAniSalto, this.alturaAniSalto);
        this.ryuJump[3] = ryuSpriteSheet.getSubimage(273, 568 - this.alturaAniSalto, this.anchoAniSalto, this.alturaAniSalto);
        this.ryuJump[4] = ryuSpriteSheet.getSubimage(347, 568 - this.alturaAniSalto, this.anchoAniSalto, this.alturaAniSalto);
        this.ryuJump[5] = ryuSpriteSheet.getSubimage(424, 568 - this.alturaAniSalto, this.anchoAniSalto, this.alturaAniSalto);
        this.ryuJump[6] = ryuSpriteSheet.getSubimage(505, 568 - this.alturaAniSalto, this.anchoAniSalto, this.alturaAniSalto);
        this.anchoAgacharse = 73;
        this.alturaAgacharse = 64;
        this.ryuCrouch[0] = ryuSpriteSheet.getSubimage(8, 668 - this.alturaAgacharse, this.anchoAgacharse, this.alturaAgacharse);
        this.ryuCrouch[1] = ryuSpriteSheet.getSubimage(88, 668 - this.alturaAgacharse, this.anchoAgacharse, this.alturaAgacharse);
        this.ryuJumpR[0] = ryuSpriteSheet.getSubimage(177, 684 - height - 10, width, height + 10);
        this.ryuJumpR[1] = ryuSpriteSheet.getSubimage(254, 684 - height, width, height + 10);
        this.ryuJumpR[2] = ryuSpriteSheet.getSubimage(331, 684 - height, 93, height + 10);
        this.ryuJumpR[3] = ryuSpriteSheet.getSubimage(445, 684 - height, width, height + 10);
        this.ryuJumpR[4] = ryuSpriteSheet.getSubimage(517, 684 - height, 117, height + 10);
        this.ryuJumpR[5] = ryuSpriteSheet.getSubimage(11, 803 - height, width, height + 10);
        this.ryuJumpR[6] = ryuSpriteSheet.getSubimage(104, 803 - height, width, height + 10);
        this.ryuJumpR[7] = ryuSpriteSheet.getSubimage(184, 803 - height, width, height + 10);
        this.ryuJumpL[7] = ryuSpriteSheet.getSubimage(177, 684 - height - 10, width, height + 10);
        this.ryuJumpL[6] = ryuSpriteSheet.getSubimage(254, 684 - height, width, height + 10);
        this.ryuJumpL[5] = ryuSpriteSheet.getSubimage(331, 684 - height, 93, height + 10);
        this.ryuJumpL[4] = ryuSpriteSheet.getSubimage(445, 684 - height, width, height + 10);
        this.ryuJumpL[3] = ryuSpriteSheet.getSubimage(517, 684 - height, 117, height + 10);
        this.ryuJumpL[2] = ryuSpriteSheet.getSubimage(11, 803 - height, width, height + 10);
        this.ryuJumpL[1] = ryuSpriteSheet.getSubimage(104, 803 - height, width, height + 10);
        this.ryuJumpL[0] = ryuSpriteSheet.getSubimage(184, 803 - height, width, height + 10);
        this.ryuLP[0] = ryuSpriteSheet.getSubimage(433, 794 - height, width + 6, height);
        this.ryuLP[1] = ryuSpriteSheet.getSubimage(523, 794 - height, width + 36, height);
        this.demoraLP = 95;
        this.ryuMP[0] = ryuSpriteSheet.getSubimage(26, 917 - height, width, height);
        this.ryuMP[1] = ryuSpriteSheet.getSubimage(106, 917 - height, width, height);
        this.ryuMP[2] = ryuSpriteSheet.getSubimage(189, 916 - height, width + 39, height);
        this.demoraMP = 105;
        this.ryuHP[0] = ryuSpriteSheet.getSubimage(309, 915 - height, width + 10, height);
        this.ryuHP[1] = ryuSpriteSheet.getSubimage(403, 915 - height, width + 33, height);
        this.ryuHP[2] = ryuSpriteSheet.getSubimage(517, 915 - height, width + 7, height);
        this.ryuHP[3] = ryuSpriteSheet.getSubimage(4, 1031 - height, width + 7, height);
        this.ryuHP[4] = ryuSpriteSheet.getSubimage(96, 1031 - height, width, height);
        this.demoraHP = 110;
        this.ryuLK[0] = ryuSpriteSheet.getSubimage(176, 1031 - height, width + 15, height);
        this.ryuLK[1] = ryuSpriteSheet.getSubimage(270, 1031 - height, width, height);
        this.ryuLK[2] = ryuSpriteSheet.getSubimage(342, 1031 - height, width + 40, height);
        this.ryuLK[3] = ryuSpriteSheet.getSubimage(457, 1031 - height, width, height);
        this.ryuLK[4] = ryuSpriteSheet.getSubimage(536, 1031 - height, width + 15, height);
        this.demoraLK = 90;
        this.ryuMK[0] = ryuSpriteSheet.getSubimage(39, 1141 - height, 67, height);
        this.ryuMK[1] = ryuSpriteSheet.getSubimage(120, 1141 - height, 65, height);
        this.ryuMK[2] = ryuSpriteSheet.getSubimage(199, 1141 - height, 118, height);
        this.ryuMK[3] = ryuSpriteSheet.getSubimage(39, 1141 - height, 64, height);
        this.demoraMK = 110;
        this.ryuHK[0] = ryuSpriteSheet.getSubimage(328, 1142 - height, width, height);
        this.ryuHK[1] = ryuSpriteSheet.getSubimage(408, 1142 - height, width, height);
        this.ryuHK[2] = ryuSpriteSheet.getSubimage(484, 1142 - height, width + 16, height);
        this.ryuHK[3] = ryuSpriteSheet.getSubimage(5, 1257 - height, width + 40, height);
        this.ryuHK[4] = ryuSpriteSheet.getSubimage(124, 1257 - height, width + 20, height);
        this.ryuHK[5] = ryuSpriteSheet.getSubimage(232, 1257 - height, width, height);
        this.demoraHK = 100;
        this.ryuBlock[0] = ryuSpriteSheet.getSubimage(344, 794 - height, width, height);
        this.ryuBlock[1] = ryuSpriteSheet.getSubimage(258, 794 - height, width, height);
        this.ryuHitL[0] = ryuSpriteSheet.getSubimage(243, 2534, 77, 92);
        this.ryuHitL[1] = ryuSpriteSheet.getSubimage(333, 2533, 74, 91);
        this.demoraGolpe = 600;
        this.ryuWin1[0] = ryuSpriteSheet.getSubimage(437, 2764, 60, 92);
        this.ryuWin1[1] = ryuSpriteSheet.getSubimage(519, 2758, 62, 99);
        this.ryuWin1[2] = ryuSpriteSheet.getSubimage(3, 2861, 62, 119);
        this.ryuWin1[3] = ryuSpriteSheet.getSubimage(92, 2885, 67, 95);
        this.ryuWin1[4] = ryuSpriteSheet.getSubimage(178, 2886, 67, 94);
        this.ryuWin2[0] = ryuSpriteSheet.getSubimage(178, 2886, 67, 94);
        this.ryuWin2[1] = ryuSpriteSheet.getSubimage(260, 2885, 67, 94);
        this.ryuWin2[2] = ryuSpriteSheet.getSubimage(339, 2885, 67, 94);
        this.ryuWin2[3] = ryuSpriteSheet.getSubimage(418, 2885, 67, 94);
        this.ryuWin2[4] = ryuSpriteSheet.getSubimage(493, 2885, 67, 94);
        this.ryuWin2[5] = ryuSpriteSheet.getSubimage(567, 2885, 67, 94);
        this.ryuFireball[0] = ryuSpriteSheet.getSubimage(51, 1597, 74, 90);
        this.ryuFireball[1] = ryuSpriteSheet.getSubimage(139, 1602, 91, 85);
        this.ryuFireball[2] = ryuSpriteSheet.getSubimage(240, 1607, 96, 80);
        this.ryuFireball[3] = ryuSpriteSheet.getSubimage(344, 1611, 114, 77);
        this.ryuFireball[4] = ryuSpriteSheet.getSubimage(470, 1609, 114, 79);
        this.fireball[0] = ryuSpriteSheet.getSubimage(5, 3146 - h_height, h_width, h_height);
        this.fireball[1] = ryuSpriteSheet.getSubimage(82, 3146 - h_height, h_width, h_height);
        this.fireball[2] = ryuSpriteSheet.getSubimage(156, 3146 - h_height, h_width, h_height);
        this.fireball[3] = ryuSpriteSheet.getSubimage(231, 3146 - h_height, h_width, h_height);
        this.fireball[4] = ryuSpriteSheet.getSubimage(314, 3146 - h_height, h_width, h_height);
        this.fireball[5] = ryuSpriteSheet.getSubimage(389, 3146 - h_height, h_width, h_height);
        this.fireball[6] = ryuSpriteSheet.getSubimage(467, 3146 - h_height, h_width, h_height);
        this.fireball[7] = ryuSpriteSheet.getSubimage(542, 3146 - h_height, h_width, h_height);
    }
}
