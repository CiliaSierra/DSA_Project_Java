package API;

import jugador.Objeto;
import jugador.Usuario;

import java.util.List;

public interface MundoInterfaz {

    Usuario login(Usuario usuario) throws Exception; //DAO
    boolean loginBool(Usuario usuario) throws Exception; //DAO

    Usuario register(Usuario usuario) throws Exception; //DAO
    boolean registerBool(Usuario usuario) throws Exception; //DAO

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
