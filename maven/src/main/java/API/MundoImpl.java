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

    public Usuario login(Usuario usuario) throws Exception {
        try {
            return DAOImpl.getInstance().selectUserByUsernameAndPw(usuario.getNombre(), usuario.getPassword());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Usuario login2 (Usuario usuario) throws Exception {
        try {
            return UsuarioDAO.getInstance().selectUserByUsernameAndPw(usuario.getEmail(), usuario.getPassword());
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    public boolean loginBool2 (Usuario usuario){
        try{
            login2(usuario);
            return true;
        }
        catch (Exception e){
            return false;
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

    public boolean logInSara(String email, String pass){
        if(pass.equals(usuarios.get(email).getPassword())){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean cambiarPass(String nombre, String pass) {
        return false;
    }

    public Usuario register(Usuario usuario) throws Exception {     //REGISTRO EN LA BASE DE DATOS
        try {
            return DAOImpl.getInstance().insertUser(usuario);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }       //REGISTRAR BASE DE DATOS

    public boolean registerBool(Usuario usuario){
        try{
            register(usuario);
            return true;
        }
        catch (Exception e){
            return false;
        }
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
    @Override
    public boolean eliminarUsuario(String nombre) {
        return false;
    }

    public boolean deleteUser(Usuario usuario) throws Exception {     //ELIMINAR DE LA BASE DE DATOS
        try {
            DAOImpl.getInstance().delete(usuario);
            return true;
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public boolean eliminarUsuario(Usuario usuario) throws Exception{  //ELIMINAR DEL HASHMAP
        try {
            if (usuarios.get(usuario.getNombre()).getPassword().equals(usuario.getPassword())){
                return usuarios.remove(usuario.getNombre())!=null;
            }
            else
                return false;
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    public String consultarUsuario(String nombre){
        return usuarios.get(nombre).getPassword();
    }

    public boolean cambiarPass(Usuario user){         //CAMBAIR PASS HASH MAP ¿BASE DE DATOS?
        if (usuarios.get(user.getNombre())!=null) {
            if (user.getPassword().equals(usuarios.get(user.getNombre()).getPassword()))
                return false;//contraseña igual a la anterior

            else {
                usuarios.get(user.getNombre()).setPassword(user.getPassword());
                return true;//contraseña cambiada correctamente
            }
        }
        else
            return false;

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

    public List<Mapa> getMapas() {
        return mapas;
    }

    public void setMapas(List<Mapa> mapas) {
        this.mapas = mapas;
    }
}
