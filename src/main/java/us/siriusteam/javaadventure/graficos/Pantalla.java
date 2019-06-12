package us.siriusteam.javaadventure.graficos;

import us.siriusteam.javaadventure.mapa.cuadro.Cuadro;

public final class Pantalla {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    //Temporal
    private final static int LADO_SPRITE = 32;
    private final static int MASCARA_SPRITE = LADO_SPRITE-1;

    //Fin temporal

    public Pantalla(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;
        pixeles = new int[alto*ancho];
    }

    public void limpiar(){
        for(int i = 0; i < pixeles.length; i++){
            pixeles[i] = 0;
        }
    }

    public void mostrar(final int compensacionX, final int compensacionY){
        for (int y = 0; y < alto; y++){
            int posicionY = y+compensacionY;

            if(posicionY < 0 || posicionY >= alto){
                continue;
            }

            for (int x = 0; x < ancho; x++){
                int posicionX = x + compensacionX;
                if(posicionX < 0 || posicionX >= ancho){
                    continue;
                }
                //temporal
                pixeles[posicionX + posicionY * ancho]  = Sprite.ASFALTO.pixeles[(x & MASCARA_SPRITE)
                        + (y & MASCARA_SPRITE) * LADO_SPRITE];
            }
        }
    }

    public void mostrarCuadro(int compensacionx, int compensacionY, Cuadro cuadro){
        for(int y = 0; y < cuadro.sprite.getLado(); y++){
            int posicionY = y + compensacionY;
            for(int x = 0; x < cuadro.sprite.getLado(); x++){
                int posicionX = x + compensacionx;
                if(posicionX < 0 || posicionX > ancho || posicionY < 0 || posicionY > alto){
                    break;
                }

                pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.getLado()];
            }
        }
    }
}
