package API;

import celdas.Mapa;
import dao.DAOImpl;
import jugador.Objeto;
import jugador.Pack;
import jugador.Usuario;

import java.util.*;

public class MundoImpl implements MundoInterfaz{

    public Map<String, Usuario> usuarios = new HashMap<>();
    List<Mapa> mapas = new ArrayList<>();

    //usuario
    public boolean crearUsuario(Usuario u){
        if(usuarios.containsKey(u.getNombre()))
            return false;
        else{
            usuarios.put(u.getNombre(), u);
            return true;
        }
    }
    public boolean eliminarUsuario(String nombre){
        return usuarios.remove(nombre) != null;
    }
    public Usuario consultarUsuario(String nombre){
        return usuarios.get(nombre);
    }
    public Usuario login(String nombre, String password) throws Exception{
        return DAOImpl.getInstance().selectUserByUsernameAndPw(nombre, password);
    }
    public List<Usuario> listaUsuarios (){

        List<Usuario> user = new ArrayList<>();
        //recoremos el HashMap de usuarios y vamos añadiendolo a las lista de usuarios
        //user.add(usuarios.values());//Me devuelve una coleccion de los usuarios
        return user;
    }
    //objeto
    public int añadirObjetoAUsuario(Usuario u, Objeto o, int cantidad){
       return usuarios.get(u.getNombre()).invAdd(o, cantidad);
    }
    public int eliminarObjetoDeUsuario(Usuario u, String nombreObjeto, int cantidad){
        Objeto obj = objFromNombre(u,nombreObjeto);
        return u.invRemove(obj, cantidad);
    }
    private Objeto objFromNombre(Usuario u, String nombreObjeto){
        for(Pack pack : u.getInventario()){
            if(pack.getObjeto().getNombre().equals(nombreObjeto)){
                return pack.getObjeto();
            }
        }
        return null;        //Crear excepción nueva propia
    }
}
