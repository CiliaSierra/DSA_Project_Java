package API;

import jugador.Objeto;
import jugador.Usuario;

import java.util.List;

public interface MundoInterfaz {

    Usuario login2(Usuario usuario) throws Exception; //DAO
    boolean loginBool2(Usuario usuario) throws Exception; //DAO

    public boolean register (Usuario usuario) throws Exception;

    boolean deleteUser(Usuario usuario) throws Exception; //DAO

    boolean logInSara(String nombre, String pass);
    boolean cambiarPass(String nombre, String pass);
    boolean crearUsuario(Usuario u) throws Exception;
    boolean eliminarUsuario(String nombre);
    String consultarUsuario(String nombre);
    List<Usuario> listaUsuario();
    Usuario unUsuario(Usuario user);
    Usuario unUsuario2(String idemail);
    List<Object> listaUsuarios ();

}
