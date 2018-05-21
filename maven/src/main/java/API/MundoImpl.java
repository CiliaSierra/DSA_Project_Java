package API;

import celdas.Mapa;
import dao.DAOImpl;
import jugador.Objeto;
import jugador.Pack;
import jugador.Usuario;
import org.apache.log4j.Logger;

import java.util.*;

import static java.util.Collections.list;
import static java.util.Collections.singletonList;

public class MundoImpl implements MundoInterfaz {

    private static MundoImpl mundoImpl;

    private MundoImpl(){}

    public static MundoImpl getInstance(){
        if(mundoImpl == null) {
            mundoImpl = new MundoImpl();
            mundoImpl.setMapas(Mapa.cargarMapas());
        }
        return mundoImpl;
    }


    public Map<String, Usuario> usuarios = new HashMap<>();
    private List<Mapa> mapas = new ArrayList<>();




    public Usuario login(Usuario usuario) throws Exception {
        try {
            return DAOImpl.getInstance().selectUserByUsernameAndPw(usuario.getNombre(), usuario.getPassword());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Usuario register(Usuario usuario) throws Exception {
        try {
            return DAOImpl.getInstance().insertUser(usuario);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public boolean deleteUser(Usuario usuario) throws Exception {
        try {
            DAOImpl.getInstance().delete(usuario);
            return true;
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

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
    public Objeto objFromNombre(Usuario u, String nombreObjeto){
        for(Pack pack : u.getInventario()){
            if(pack.getObjeto().getNombre().equals(nombreObjeto)){
                return pack.getObjeto();
            }
        }
        return null;        //Crear excepción nueva propia
    }

    public List<Mapa> getMapas() {
        return mapas;
    }

    public void setMapas(List<Mapa> mapas) {
        this.mapas = mapas;
    }
}
