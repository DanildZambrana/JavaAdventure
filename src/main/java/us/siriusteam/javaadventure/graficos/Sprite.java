package us.siriusteam.javaadventure.graficos;

public final class Sprite {
    private final int lado;

    private int x;
    private int y;

    public int[] pixeles;
    private final HojaSprites hojaSprites;

    //Coleccion de sprites
    public static Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprites.desierto);
    //Fin de la collecion

    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hojaSprites) {
        this.lado = lado;
        pixeles = new int[lado * lado];

        this.x = columna * lado;
        this.y = fila * lado;
        this.hojaSprites = hojaSprites;



        for(int i = 0; i < lado; i++){
            for(int x = 0; x < lado; x++){
                pixeles[x + i * lado] = hojaSprites.pixeles[(x + this.x) + (i + this.y) * hojaSprites.getAncho()];
            }
        }
    }

    public int getLado() {
        return lado;
    }
}
