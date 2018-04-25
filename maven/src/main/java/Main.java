import celdas.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Mundo m = new Mundo();

    public static void main(String[] args) {

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

        ObjectMapper om = new ObjectMapper();
        try {
            System.out.println(om.writerWithView(Views.Normal.class).writeValueAsString(mapa));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //mapa.mostrarMapa();;
        try {
            mapa.guardarMapa();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
