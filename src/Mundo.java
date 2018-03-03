package src;

import java.util.*;

public class Mundo {

    public Map<String, src.Usuario> usuarios = new HashMap<>();
    List<Mapa> mapas = new ArrayList<>();

    //usuario
    public boolean crearUsuario(src.Usuario u){
        if(usuarios.containsKey(u.getNombre()))
            return false;
        else{
            usuarios.put(u.getNombre(), u);
            return true;
        }
    }
    public boolean eliminarUsuario(String nombre){
        if(usuarios.remove(nombre) == null){
            return false;
        }
        return true;
    }
    public src.Usuario consultarUsuario(String nombre){
        return usuarios.get(nombre);
    }

    //objeto
    public void añadirObjetoAUsuario(src.Usuario u, Objeto o){
        usuarios.get(u.getNombre()).inventario.add(o);
    }
    public LinkedList consultarObjetosDeUsuario(src.Usuario u){
        return u.inventario;
    }
    public src.Objeto consultarObjetoDeUsuario(src.Usuario u, String nombreObjeto){
        for(src.Objeto o : u.inventario)
            if(o.nombre.equals(nombreObjeto))
                return o;
        return null;
    }
    public boolean eliminarObjetoDeUsuario(src.Usuario u, String nombreObjeto){
        for(src.Objeto o : u.inventario)
            if(o.nombre.equals(nombreObjeto)){
                u.inventario.remove(o);
                return true;
            }
        return false;
    }
    public void transferirObjetoEntreUsuarios(src.Usuario origen, src.Usuario destino, Objeto o){
        if(origen.inventario.remove(o))
            destino.inventario.add(o);
    }
}
