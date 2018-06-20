package API;

import jugador.Objeto;
import jugador.Usuario;

import java.util.List;

public interface MundoInterfaz {

    Usuario login2(Usuario usuario) throws Exception; //DAO
    boolean loginBool2(Usuario usuario) throws Exception; //DAO

    public boolean register (Usuario usuario) throws Exception;

    boolean eliminaruser (Usuario usuario) throws Exception;

    Usuario infoUsuario (Usuario usuario) throws Exception;

    boolean infobool (Usuario usuario);

    boolean logInSara(String nombre, String pass);
    boolean cambiarPass(Usuario usuario);
    boolean crearUsuario(Usuario u) throws Exception;
    List<Usuario> listaUsuario();
    Usuario unUsuario(Usuario user);
    Usuario unUsuario2(String idemail);
    List<Object> listaUsuarios ();

}
