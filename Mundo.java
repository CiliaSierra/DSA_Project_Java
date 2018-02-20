import java.util.*;

public class Mundo {

    public Map<String, Usuario> usuarios = new HashMap<>();

    //usuario
    public boolean crearUsuario(Usuario u){
        if(usuarios.containsKey(u.nombre))
            return false;
        else{
            usuarios.put(u.nombre, u);
            return true;
        }
    }
    /*public boolean eliminarUsuario(Usuario u){
        if(usuarios.remove(u.nombre) == null){
            return false;
        }
        return true;
    }*/
    public boolean eliminarUsuario(String nombre){
        if(usuarios.remove(nombre) == null){
            return false;
        }
        return true;
    }
    public Usuario consultarUsuario(String nombre){
        return usuarios.get(nombre);
    }

    //objeto
    public void añadirObjetoAUsuario(Usuario u, Objeto o){
        usuarios.get(u.nombre).inventario.add(o);
    }
    public LinkedList consultarObjetosDeUsuario(Usuario u){
        return u.inventario;
    }
    public Objeto consultarObjetoDeUsuario(Usuario u, String nombreObjeto){
        for(Objeto o : u.inventario)
            if(o.nombre.equals(nombreObjeto))
                return o;
        return null;
    }
    public boolean eliminarObjetoDeUsuario(Usuario u, String nombreObjeto){
        for(Objeto o : u.inventario)
            if(o.nombre.equals(nombreObjeto)){
                u.inventario.remove(o);
                return true;
            }
        return false;
    }
    public void transferirObjetoEntreUsuarios(Usuario origen, Usuario destino, Objeto o){
        if(origen.inventario.remove(o))
            destino.inventario.add(o);
    }
}
