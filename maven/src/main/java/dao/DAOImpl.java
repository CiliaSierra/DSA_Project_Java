package dao;

import jugador.Usuario;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Calendar;

public class DAOImpl {

    private static DAOImpl instance = null;

    public static DAOImpl getInstance() {
        if (instance == null) instance = new DAOImpl();

        return instance;
    }

    // Specific

    public Object selectByUsernameAndPw(Object object, String username, String password) throws Exception {
        try {
            Connection con = getConnection();
            String query = getSelectWithUsernameAndPwQuery(object);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                setFieldsFromResultSet(resultSet, resultSetMetaData, object);
            }
            resultSet.beforeFirst();
            if (!resultSet.next()) {
                object = null;
            }
            preparedStatement.close();
            con.close();

            return object;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private Connection getConnection() throws SQLException, Exception {

        //com.mysql.cj.jdbc.Driver, lo detecta automaticamente
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "phpmyadmin@localhost", "phpmyadmin");
            System.out.println("Connected to database");

            return con;
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        }
    }

    private String getSelectWithUsernameAndPwQuery(Object object) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE name=?");
        query.append(" AND password=?");

        return query.toString();
    }

    private void setFieldsFromResultSet(ResultSet resultSet, ResultSetMetaData resultSetMetaData, Object object) throws Exception {
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            String columnType = resultSetMetaData.getColumnTypeName(i);
            String columnName = resultSetMetaData.getColumnLabel(i);
            switch (columnType) {
                case "VARCHAR":
                    String resultString = resultSet.getString(i);
                    if (resultString != null) {
                        setField(resultString, columnName, object);
                    }
                    break;
                case "INT":
                    int resultInt = resultSet.getInt(i);
                    setField(resultInt, columnName, object);
                    break;
                case "DOUBLE":
                    double resultDouble = resultSet.getDouble(i);
                    setField(resultDouble, columnName, object);
                    break;
                case "TINYINT":
                    boolean resultBoolean = resultSet.getBoolean(i);
                    setField(resultBoolean, columnName, object);
                    break;
                case "DATETIME":
                    java.util.Date resultDate = resultSet.getDate(i);
                    Calendar resultCalendar = Calendar.getInstance();
                    resultCalendar.setTime(resultDate);
                    setField(resultCalendar, columnName, object);
                    break;
                default:
                    break;
            }
        }
    }

    private void setField(Object result, String name, Object object) throws Exception {
        Method method;
        try {
            method = object.getClass().getMethod(getSetterName(name), object.getClass().getDeclaredField(name).getType());
            method.invoke(object, result);
        } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            throw new Exception(e);
        }
    }

    private String getSetterName(String fieldName) {
        StringBuilder setterName = new StringBuilder("set");
        setterName.append(capitalizeName(fieldName));

        return setterName.toString();
    }

    private String capitalizeName(String name) {
        String capitalizedFieldName;
        capitalizedFieldName = name.substring(0, 1).toUpperCase() + name.substring(1);

        return capitalizedFieldName;
    }

    public Usuario selectUserByUsernameAndPw(String username, String password) throws Exception {
        Usuario u = new Usuario();
        try {
            return (Usuario) selectByUsernameAndPw(u, username, password);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
