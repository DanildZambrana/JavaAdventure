package us.siriusteam.javaadventure.mapa;

import us.siriusteam.javaadventure.graficos.Pantalla;

public abstract class Mapa {
    private int ancho;
    private int alto;

    private int[] cuadros;

    //Crea mapa aleatorio

    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.cuadros = new int[ancho*alto];
        generarMapa();
    }

    public void generarMapa(){

    }

    //Carga mapa desde una ruta
    public Mapa(String ruta){
        cargarMapa(ruta);
    }

    public void cargarMapa(String ruta){

    }


    //Actualizar y Mostrar los mapas
    public void actualizar(){

    }

    public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla){

    }
}
