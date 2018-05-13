package dao;

import jugador.Usuario;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static dao.Sesion.insert;

public class DAOImpl {

    private static DAOImpl instance = null;

    public static DAOImpl getInstance() {
        if (instance == null) instance = new DAOImpl();

        return instance;
    }

    private Connection getConnection() throws Exception {

        //com.mysql.cj.jdbc.Driver, lo detecta automaticamente
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDb", "phpmyadmin@localhost", "phpmyadmin");
            System.out.println("Connected to database");//Utilitzar Logger

            return con;
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        }
    }

    // Generic

    public Object insert(Object object) throws Exception {

        try {
            Connection con = getConnection();
            String query = getInsertQuery(object);

            PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            addClassFieldsParameters(object, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) setField(generatedKeys.getInt(1), "id", object);
            preparedStatement.close();
            con.close();

            return object;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    // Specific

        //Usuario

    public Object selectByUsernameAndPw(Object object, String username, String password) throws Exception{
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
    }

    private String getSelectWithUsernameAndPwQuery(Object object) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(object.getClass().getSimpleName());
        query.append(" WHERE name=?");
        query.append(" AND password=?");

        return query.toString();
    }

    public Usuario selectUserByUsernameAndPw(String username, String password) throws Exception {
        Usuario u = new Usuario();
        try {
            return (Usuario) selectByUsernameAndPw(u, username, password);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Usuario insertUser(Usuario usuario) throws Exception {
        try {
            return (Usuario) insert(usuario);
        } catch (Exception e) {
            throw new Exception(e);
        }
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

    private String getInsertQuery(Object object) {
        StringBuffer query = new StringBuffer("INSERT INTO ");
        query.append(object.getClass().getSimpleName());
        query.append(" (");
        addFieldsInsertQuery(object, query);
        query.append(")");
        query.append(" VALUES (");
        addInterrogantsInsertQuery(object, query);
        query.append(")");

        return query.toString();
    }

    private void addClassFieldsParameters(Object object, PreparedStatement pstm) throws Exception {
        int i = 1;
        try {
            for (Field field : getNonGenericObjectDeclaredFields(object)) {
                Method method = object.getClass().getMethod(getGetterName(field.getName()));
                Object methodObjectResultant = getMethodObjectResultant(object, method, field);
                pstm.setObject(i, methodObjectResultant);
                i++;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    private Object getMethodObjectResultant(Object object, Method method, Field field) throws Exception {
        Object methodObjectResulted = null;
        try {
            if (field.getType() == Calendar.class) {
                Calendar calendar = (Calendar) method.invoke(object);
                methodObjectResulted = new java.sql.Date(calendar.getTime().getTime());
            } else {
                methodObjectResulted = method.invoke(object);
            }
            return methodObjectResulted;
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new Exception(e);
        }

    }

    private void addInterrogantsInsertQuery(Object object, StringBuffer query) {
        for (Field ignore : getNonGenericObjectDeclaredFields(object)) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
    }

    private void addFieldsInsertQuery(Object object, StringBuffer query) {
        for (Field f : getNonGenericObjectDeclaredFields(object)) {
            query.append(f.getName()).append(",");
        }
        query.deleteCharAt(query.length() - 1);
    }

    private String getGetterName(String fieldName) {
        StringBuilder getterName = new StringBuilder("get");
        getterName.append(capitalizeName(fieldName));

        return getterName.toString();
    }

    private List<Field> getNonGenericObjectDeclaredFields(Object object) {
        List<Field> nonGenericObjectDeclaredFields = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getType() == String.class || field.getType() == Integer.class || field.getType() == int.class || field.getType() == boolean.class || field.getType() == Boolean.class || field.getType() == Double.class || field.getType() == double.class || field.getType() == Calendar.class) {
                nonGenericObjectDeclaredFields.add(field);
            }
        }

        return nonGenericObjectDeclaredFields;
    }
}
