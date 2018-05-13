import API.MundoImpl;
import celdas.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Sesion;
import jugador.Usuario;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static MundoImpl m = new MundoImpl();

    public static void main(String[] args) {

        //Declaro las celdas y las inicializo
        ArrayList<ArrayList<Celda>> c = new ArrayList<ArrayList<Celda>>();
        ArrayList<Celda> c1 = new ArrayList<>();
        c1.add(new Muro());  //1
        c1.add(new Muro());  //2
        c1.add(new Muro());  //3
        c1.add(new Muro());  //4
        ArrayList<Celda> c2 = new ArrayList<>();
        c2.add(new Rio());  //5
        c2.add(new Rio());  //6
        c2.add(new Rio());  //7
        c2.add(new Cofre(Cofre.Tipo.oro));  //8
        ArrayList<Celda> c3 = new ArrayList<>();
        c3.add(new Muro());  //9
        c3.add(new Camino());  //10
        c3.add(new Camino());  //11
        c3.add(new Muro());  //12
        ArrayList<Celda> c4 = new ArrayList<>();
        c4.add(new Muro());  //13
        c4.add(new Muro());  //14
        c4.add(new Muro());  //15
        c4.add(new Muro());  //16
        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(c4);


        Mapa mapa = new Mapa("Proba", 4,4);
        mapa.setCeldas(c);
        mapa.mostrarMapa();

        ObjectMapper om = new ObjectMapper();
        try {
            mapa.guardarMapa();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Mapa m = new Mapa();
        m.setNombre("Proba");
        try {
            m.cargarMapa();
        } catch (IOException e) {
            e.printStackTrace();
        }


       ObjectMapper objectMapper = new ObjectMapper();

        //Creo los Usuarios
        List<Usuario> listaUsuers = Stream.of(
                new Usuario("Sara","123456","sara@email",5,5),
                new Usuario("Cilia", "1111", "cilia@email",6,75))
                .collect(Collectors.toList());

        String arrayToJson = null;
        try {
            arrayToJson = objectMapper.writeValueAsString(listaUsuers);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("Lista Usuarios");
        System.out.println(arrayToJson);

    }
}
