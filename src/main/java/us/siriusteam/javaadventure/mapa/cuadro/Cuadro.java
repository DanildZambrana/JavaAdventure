package us.siriusteam.javaadventure.mapa.cuadro;

import us.siriusteam.javaadventure.graficos.Pantalla;
import us.siriusteam.javaadventure.graficos.Sprite;

public abstract class Cuadro {
    public int x;
    public int y;

    public Sprite sprite;

    //Coleccion de cuadros
    public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ASFALTO);
    //Fin de collecion de cuadros

    public Cuadro(Sprite sprite) {
        this.sprite = sprite;
    }

    public void mostrar(int x, int y, Pantalla pantalla){

    }

    public boolean solido(){
        return false;
    }
}
