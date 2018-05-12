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

        System.out.println(Sesion.createTable(Usuario.class));
        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Declaro las celdas y las inicializo
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
