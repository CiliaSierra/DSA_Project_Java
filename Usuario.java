import java.util.LinkedList;
import java.util.List;

public class Usuario {

    String nombre;
    String password;
    int nivel;
    int ataque;
    int defensa;

    public LinkedList<Objeto> inventario = new LinkedList<Objeto>();

    public Usuario(String nombre, String password, int nivel, int ataque, int defensa){
        this.nombre = nombre;
        this.password = password;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defensa = defensa;
    }
}
