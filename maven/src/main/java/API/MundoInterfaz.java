package API;

import jugador.Objeto;
import jugador.Usuario;

import java.util.List;

public interface MundoInterfaz {

    Usuario login(Usuario usuario) throws Exception;

    Usuario register(Usuario usuario) throws Exception;
    boolean registerBool(Usuario usuario) throws Exception;

    boolean deleteUser(Usuario usuario) throws Exception;

    boolean logInSara(String nombre, String pass);
    boolean cambiarPass(String nombre, String pass);
    boolean crearUsuario(Usuario u) throws Exception;
    boolean eliminarUsuario(String nombre);
    String consultarUsuario(String nombre);
    List<Object> listaUsuarios ();

}
