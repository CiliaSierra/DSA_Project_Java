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


public class NuDAOImpl {

    final static Logger logger = Logger.getLogger(DAOImpl.class);

    private static NuDAOImpl instance = null;

    public static NuDAOImpl getInstance() {
        if (instance == null) instance = new NuDAOImpl();

        return instance;
    }

    private Connection getConnection() throws Exception {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("147.83.7.203", "dsa0", "Mazinger72");

            logger.info("Conectado a la base de datos");


            return con;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Usuario selectUserByUsernameAndPw(String username, String password) throws Exception {
        Connection con = getConnection();
        Usuario u = new Usuario();
        String query = getSelectWithUsernameAndPwQuery(object);

        try {
            return (Usuario) selectByUsernameAndPw(u, username, password);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
