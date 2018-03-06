package src;

import java.util.*;

public class Mundo {

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
        if(usuarios.remove(nombre) == null){
            return false;
        }
        return true;
    }
    public Usuario consultarUsuario(String nombre){
        return usuarios.get(nombre);
    }

    //objeto
    public boolean añadirObjetoAUsuario(Usuario u, Objeto o){
       return usuarios.get(u.getNombre()).invAdd(o);
    }
    public boolean eliminarObjetoDeUsuario(Usuario u, String nombreObjeto){
        Objeto obj = objFromNombre(u,nombreObjeto);
        return u.invRemove(obj);
    }
    private Objeto objFromNombre(Usuario u, String nombreObjeto){
        for(Pack pack : u.getInventario()){
            if(pack.objeto.nombre==nombreObjeto){
                return pack.objeto;
            }
        }
        return null;        //Crear excepción nueva propia
    }
}
