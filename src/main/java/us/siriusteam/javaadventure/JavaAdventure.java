package us.siriusteam.javaadventure;

import us.siriusteam.javaadventure.control.Teclado;
import us.siriusteam.javaadventure.graficos.Pantalla;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class JavaAdventure extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static volatile boolean ejecutandose = false;

    private static final String NOMBRE = "Juego";

    private static int aps = 0;
    private static int fps = 0;

    private static int x = 0;
    private static int y = 0;
    private static int velocidad = 1;

    private static JFrame ventana;
    private static Thread hilo;
    private static Teclado teclado;
    private static Pantalla pantalla;

    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
    private static final ImageIcon ICONO = new ImageIcon(JavaAdventure.class.getResource("/Icono/Icono.png"));
    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
    private JavaAdventure(){
        setPreferredSize(new Dimension(ANCHO, ALTO));

        pantalla = new Pantalla(ANCHO, ALTO);

        teclado = new Teclado();
        addKeyListener(teclado);


        ventana = new JFrame(NOMBRE);
        ventana.setIconImage(ICONO.getImage());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        JavaAdventure juego = new JavaAdventure();
        juego.iniciar();
    }

    private synchronized void iniciar(){
        ejecutandose = true;

        hilo = new Thread(this, "Graficos");
        hilo.start();
    }

    private synchronized void detener(){
        ejecutandose = false;
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void actualizar(){
        teclado.actualizar();
        if (teclado.arriba) {
            y += velocidad;
        }
        if (teclado.abajo) {
            y -= velocidad;
        }
        if (teclado.izquierda) {
            x += velocidad;
        }
        if (teclado.derecha) {
            x -= velocidad;
        }
        aps++;
    }

    private void mostrar(){
        BufferStrategy estrategia = getBufferStrategy();

        if(estrategia == null){
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();
        pantalla.mostrar(x, y);

        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

        /*for (int i = 0; i < pixeles.length; i++){
            pixeles[i] = pantalla.pixeles[i];
        }*/
        Graphics g =  estrategia.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.white);
        g.fillRect(ANCHO / 2, ALTO / 2, 32, 32);
        g.dispose();

        estrategia.show();
        fps++;
    }

    public void run() {
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        double delta = 0;
        requestFocus();

        while (ejecutandose){

            final long INICIO_BUCLE = System.nanoTime();
            tiempoTranscurrido = INICIO_BUCLE - referenciaActualizacion;
            referenciaActualizacion = INICIO_BUCLE;
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1){
                actualizar();
                delta--;
            }

            mostrar();

            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}
