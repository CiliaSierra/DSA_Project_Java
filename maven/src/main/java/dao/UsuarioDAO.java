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

        String jdbc = "jdbc:mysql://147.83.7.203:22/juego";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(jdbc, "dsa0", "Mazinger72");

            logger.info("Conectado a la base de datos");


            return con;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Usuario selectUserByUsernameAndPw(String email, String password) throws Exception {

        Usuario u = new Usuario();
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {

            String query = "SELECT * FROM Usuario WHERE email=? AND password=? ";

            st = con.prepareStatement(query);
            st.setObject(1, email);
            st.setObject(2, password);

            rs = st.executeQuery();
            if (rs.next()){
                u = convertir(rs);
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
        return u;
    }

    public Usuario convertir (ResultSet rs) throws Exception{

        String email = rs.getString("email");
        String password = rs.getString("password");
        Usuario u = new Usuario(email,password);
        u.setId(rs.getInt("id"));
        return u;
    }



}
