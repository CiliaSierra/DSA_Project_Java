package src;

import java.util.Scanner;

public class Main {

    public static src.Mundo m = new src.Mundo();

    public static void main(String[] args) {

        Celda c1=new src.Muro();
        Celda c2=new src.Muro();
        Celda c3=new src.Camino();
        Celda c4=new Rio();
        Mapa mapa = new Mapa(2,2);
        mapa.celdas[0][0] =c1;
        mapa.celdas[0][1] =c2;
        mapa.celdas[1][0] =c3;
        mapa.celdas[1][1] =c4;
        mapa.mostrarMapa();
    }
}
