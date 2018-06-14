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

    public boolean loginBool(Usuario usuario){
        try{
            login(usuario);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    public boolean logInSara(String nombre, String pass){
        if(pass.equals(usuarios.get(nombre).getPassword())){
            return true;
        }
        else
            return false;
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

    public String consultarUsuario(String nombre){
        return usuarios.get(nombre).getPassword();
    }

    public boolean cambiarPass(String nombre, String pass){

            if (pass.equals(usuarios.get(nombre).getPassword()))
                return false;//contraseña igual a la anterior

            else {
                usuarios.get(nombre).setPassword(pass);
                return true;//contraseña cambiada correctamente
            }

    }

    public List<Usuario> listaUsuarios (){

        List<Usuario> user = new ArrayList<>();
        //recoremos el HashMap de usuarios y vamos añadiendolo a las lista de usuarios
        //user.add(usuarios.values());//Me devuelve una coleccion de los usuarios
        return user;
    }

    public List<Integer> listaObjetos(String user){
        List<Integer> obj = new ArrayList<>();
        obj.add(usuarios.get(user).getObj1());
        obj.add(usuarios.get(user).getObj2());
        obj.add(usuarios.get(user).getObj3());
        obj.add(usuarios.get(user).getObj4());
        return obj;
    }

    public List<Mapa> getMapas() {
        return mapas;
    }

    public void setMapas(List<Mapa> mapas) {
        this.mapas = mapas;
    }
}
