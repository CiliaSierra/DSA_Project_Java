package dao;

import jugador.Usuario;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;



public class UsuarioDAO {

    final static Logger logger = Logger.getLogger(DAOImpl.class);

    private static UsuarioDAO instance = null;

    public static UsuarioDAO getInstance() {
        if (instance == null) instance = new UsuarioDAO();

        return instance;
    }

    private Connection getConnection() throws Exception {

        String jdbc = "jdbc:mysql://localhost:3306/juego";

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(jdbc, "root", "Da3485ni");

            logger.info("Conectado a la base de datos");


            return con;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    // Funciones Principales

    public Usuario selectUserByUsernameAndPw(String email, String password) throws Exception {

        Usuario u = new Usuario();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Usuario WHERE email=? AND password=? ";
        try {



            st = con.prepareStatement(query);
            st.setObject(1, email);
            st.setObject(2, password);

            rs = st.executeQuery();
            if (rs.next()){
                u = convertirLogin(rs);
            }
            else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (rs != null){
                try{ rs.close();
            } catch (Exception e){
                    new Exception();
                }
        }
        if (st != null){
            try { st.close();}
            catch (Exception e) {
                new Exception();
            }

            }
        }
        logger.info(u.getEmail()+" se ha conectado");
        return u;
    } // Login

    public void insertUser(Usuario usuario) throws Exception {

        Connection con = getConnection();
        int lastID = lastID();
        PreparedStatement st = null;
        String query = "insert into Usuario (id,nombre,password,email) Values (?,?,?,?)";

        try {

            st = con.prepareStatement(query);
            st.setInt(1,lastID);
            st.setString(2,usuario.getNombre());
            st.setString(3,usuario.getPassword());
            st.setString(4,usuario.getEmail());

            if (st.executeUpdate() ==0){
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (st != null){
                try { st.close();
                    logger.info(usuario.getEmail()+" se ha registrado con nombre "+usuario.getNombre());
                }
                catch (Exception e) {
                    new Exception();
                }

            }
        }

    } // Register

    public void cambiarPassword (Usuario usuario) throws Exception{

        Connection con = getConnection();
        PreparedStatement st = null;
        String query = "UPDATE Usuario SET password =? WHERE email =?";

        try {

            st = con.prepareStatement(query);
            st.setString(1,usuario.getPassword());
            st.setString(2,usuario.getEmail());

            if (st.executeUpdate() ==0){
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (st != null){
                try { st.close();
                    logger.info("El usuario con mail: "+usuario.getEmail()+" ha modificado su password por: "+usuario.getPassword());
                }
                catch (Exception e) {
                    new Exception();
                }

            }
        }

    } // Cambiar password

    public void eliminarUsuario (Usuario usuario) throws Exception{

        Connection con = getConnection();
        PreparedStatement st = null;
        String query = "DELETE FROM Usuario WHERE email=? AND password=?";

        try {

            st = con.prepareStatement(query);
            st.setString(1,usuario.getEmail());
            st.setString(2,usuario.getPassword());

            if (st.executeUpdate() ==0){
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (st != null){
                try { st.close();
                    logger.info("El usuario con mail: "+usuario.getEmail()+" ha sido eliminado");
                }
                catch (Exception e) {
                    new Exception();
                }

            }
        }


    } // Eliminar user

    public Usuario infoUsuario (Usuario usuario) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Usuario WHERE email=?";

        try {

            st = con.prepareStatement(query);
            st.setString(1,usuario.getEmail());

            rs = st.executeQuery();
            if (rs.next()){
                usuario = convertirLogin(rs);
            }
            else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (rs != null){
                try{ rs.close();
                } catch (Exception e){
                    new Exception();
                }
            }
            if (st != null){
                try { st.close();}
                catch (Exception e) {
                    new Exception();
                }

            }
        }
        logger.info("Info de: "+usuario.getEmail());
        return usuario;



    }

    // Funciones Auxiliares

    public Usuario convertirLogin (ResultSet rs) throws Exception{

        String email = rs.getString("email");
        String password = rs.getString("password");
        Usuario u = new Usuario(email,password);
        u.setId(rs.getInt("id"));
        return u;
    }

    public Usuario convertirinfo (ResultSet rs) throws Exception{



        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String imagen = rs.getString("imagen");
        int posicion = rs.getInt("ultimaposicion");
        int obj1 = rs.getInt("Obj1");
        int obj2 = rs.getInt("Obj2");
        int obj3 = rs.getInt("Obj3");
        int obj4 = rs.getInt("Obj4");

        Usuario u = new Usuario(id,nombre,email,password,imagen,posicion,obj1,obj2,obj3,obj4);
        return u;
    }

    public int lastID () throws Exception{
        int lasid;
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String query = "SELECT MAX(id) AS id  from Usuario;";

        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            if (rs.next()){
                lasid = rs.getInt("id");
            }
            else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            if (rs != null){
                try{ rs.close();
                } catch (Exception e){
                    new Exception();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    new Exception();
                }

            }


                }
        return lasid;
    }
}
