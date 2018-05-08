import API.MundoImpl;
import celdas.*;
import dao.Sesion;
import jugador.Usuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static MundoImpl m = new MundoImpl();

    public static void main(String[] args) {
        
        System.out.println(Sesion.createTable(Usuario.class));
        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Celda> c = new ArrayList<>();

        c.add(new Muro());  //1
        c.add(new Muro());  //2
        c.add(new Muro());  //3
        c.add(new Muro());  //4
        c.add(new Rio());  //5
        c.add(new Rio());  //6
        c.add(new Rio());  //7
        c.add(new Cofre(Cofre.Tipo.oro));  //8
        c.add(new Muro());  //9
        c.add(new Camino());  //10
        c.add(new Camino());  //11
        c.add(new Muro());  //12
        c.add(new Muro());  //13
        c.add(new Muro());  //14
        c.add(new Muro());  //15
        c.add(new Muro());  //16

        Mapa mapa = new Mapa("Proba", 4,4);
        mapa.llenarMapa(c);
        try {
            mapa.guardarMapa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        Mapa m = new Mapa();
        m.setNombre("Proba");
        try {
            m.cargarMapa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(m.getCeldas());
        */
    }
}
