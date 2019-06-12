package us.siriusteam.javaadventure.graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    //collecion de hojas de sprites.
    public static HojaSprites desierto = new HojaSprites("/Texturas/Desierto.png", 320,320);
    //fin de collecion

    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;
        pixeles = new int[ancho * alto];

        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));

            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAncho() {
        return ancho;
    }
}
