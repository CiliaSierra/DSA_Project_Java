package API;

import jugador.Objeto;
import jugador.Usuario;

import java.util.List;

public interface MundoInterfaz {

    Usuario login(Usuario usuario) throws Exception;

    Usuario register(Usuario usuario) throws Exception;

    boolean deleteUser(Usuario usuario) throws Exception;


    boolean crearUsuario(Usuario u);
    boolean eliminarUsuario(String nombre);
    Usuario consultarUsuario(String nombre);
    List<Usuario> listaUsuarios ();

}
