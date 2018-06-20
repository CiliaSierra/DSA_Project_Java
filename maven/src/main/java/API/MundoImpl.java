package API;

import celdas.Mapa;
import dao.DAOImpl;
import dao.UsuarioDAO;
import jugador.Usuario;
import org.eclipse.persistence.mappings.converters.ConverterClass;

import java.util.*;


public class MundoImpl implements MundoInterfaz {

    private static MundoImpl mundoImpl;

    private MundoImpl(){}

    public static MundoImpl getInstance(){
        if(mundoImpl == null) {
            mundoImpl = new MundoImpl();
        }
        return mundoImpl;
    }

    public Map<String, Usuario> usuarios = new HashMap<String, Usuario>();

    private List<Mapa> mapas = new ArrayList<>();



    public Usuario login2 (Usuario usuario) throws Exception {
        try {
            return UsuarioDAO.getInstance().selectUserByUsernameAndPw(usuario.getEmail(), usuario.getPassword());
        }
        catch (Exception e){
            throw new Exception(e);
        }
    } // Login

    public boolean loginBool2 (Usuario usuario){
        try{
            login2(usuario);
            return true;
        }
        catch (Exception e){
            return false;
        }
    } // Auxiliar Login

    public boolean cambiarPass(Usuario usuario) {
        try{
            UsuarioDAO.getInstance().cambiarPassword(usuario);
            return true;
        }
        catch (Exception e){
            return false;
        }
    } // Cambiar contrasña

    public boolean register (Usuario usuario) throws Exception {  //REGISTRO EN LA BASE DE DATOS
        try{
            UsuarioDAO.getInstance().insertUser(usuario);
            return true;
        }
        catch (Exception e){
            return false;
        }
    } // Register

    public boolean eliminaruser (Usuario usuario) throws Exception{
        try{
            UsuarioDAO.getInstance().eliminarUsuario(usuario);
            return true;
        }
        catch (Exception e){
            return false;
        }

    } // Eliminar usuario

    public Usuario infoUsuario (Usuario usuario) throws Exception {

        try {
            return MundoImpl.getInstance().infoUsuario(usuario);

        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public boolean infobool (Usuario usuario){
        try{
        infoUsuario(usuario);
        return true;
    }
    catch (Exception e){
        return false;
    }}

    public String consultarUsuario(String nombre){
        return usuarios.get(nombre).getPassword();
    }

    public List<Object> listaUsuarios (){
        List<Object> user = new ArrayList<>();
        Iterator iterator = usuarios.keySet().iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            user.add(key);
        }
        return user;
    }

    public List<Usuario> listaUsuario(){ //HACER AQUI LA FUNCION DE ENCONTRAR TODOS LOS USUARIOS
        List <Usuario> lista = new ArrayList<>();
        for (int i =0; i<usuarios.size();i++){
            boolean res = lista.add(usuarios.get(i));
        }

        return lista;
    }

    public Usuario unUsuario(Usuario user){

        String idemail = user.getEmail();

        Usuario u = usuarios.get(idemail);

        return u;
    }

    public Usuario unUsuario2(String idemail){

        Usuario u = usuarios.get(idemail);

        return u;
    }

    public List<Integer> listaObjetos(String user){
        List<Integer> obj = new ArrayList<>();
        obj.add(usuarios.get(user).getObj1());
        obj.add(usuarios.get(user).getObj2());
        obj.add(usuarios.get(user).getObj3());
        obj.add(usuarios.get(user).getObj4());
        return obj;
    }

    public boolean crearUsuario(Usuario usuario) throws Exception{  //CREAR USUARIO EN EL HASHMAP
        try{
            if(usuarios.containsKey(usuario.getNombre()))
                return false; //ya hay un ususarios creado con ese nombre
            else {
                usuarios.put(usuario.getNombre(), usuario);
                return true;
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }

    }       //REGISTRAR HASHMAP

    public List<Mapa> getMapas() {
        return mapas;
    }

    public boolean logInSara(String email, String pass){
        if(pass.equals(usuarios.get(email).getPassword())){
            return true;
        }
        else
            return false;
    }

    public void setMapas(List<Mapa> mapas) {
        this.mapas = mapas;
    }

    public boolean eliminarUsuario(String nombre) {
        return false;
    }
}
