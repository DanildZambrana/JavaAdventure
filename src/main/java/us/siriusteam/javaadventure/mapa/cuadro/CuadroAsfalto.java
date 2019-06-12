package us.siriusteam.javaadventure.mapa.cuadro;

import us.siriusteam.javaadventure.graficos.Pantalla;
import us.siriusteam.javaadventure.graficos.Sprite;

public class CuadroAsfalto extends Cuadro {
    public CuadroAsfalto(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void mostrar(int x, int y, Pantalla pantalla) {
        //super.mostrar(x, y, pantalla);
        pantalla.mostrarCuadro(x, y, this);
    }
}
