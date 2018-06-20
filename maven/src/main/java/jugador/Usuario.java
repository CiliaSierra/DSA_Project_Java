package jugador;

import celdas.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;


public class Usuario implements Serializable {
    @JsonView(Views.Normal.class)
    @SerializedName("id")
    @Expose
    private int id=1;

    @JsonView(Views.Normal.class)
    @SerializedName("nombre")
    @Expose
    private String nombre;

    @JsonView(Views.Normal.class)
    @SerializedName("password")
    @Expose
    private String password;

    @JsonView(Views.Normal.class)
    @SerializedName("email")
    @Expose
    private String email;

    @JsonView(Views.Normal.class)
    @SerializedName("imagen")
    @Expose
    private String imagen;

    @JsonView(Views.Normal.class)
    @SerializedName("ultimaposicion")
    @Expose
    private int ultimaposicion;

    @JsonView(Views.Normal.class)
    @SerializedName("Obj1")
    @Expose
    private int Obj1;

    @JsonView(Views.Normal.class)
    @SerializedName("Obj2")
    @Expose
    private int Obj2;

    @JsonView(Views.Normal.class)
    @SerializedName("Obj3")
    @Expose
    private int Obj3;

    @JsonView(Views.Normal.class)
    @SerializedName("Obj4")
    @Expose
    private int Obj4;

    //Constructores

    public Usuario(int id, String nombre, String password, String email, String imagen, int ultimaposicion, int obj1, int obj2, int obj3, int obj4){
        this.setId(id);
        this.setNombre(nombre);
        this.setPassword(password);
        this.setEmail(email);
        this.setImagen(imagen);
        this.setUltimaposicion(ultimaposicion);
        this.setObj1(obj1);
        this.setObj2(obj2);
        this.setObj3(obj3);
        this.setObj4(obj4);
    }

    public Usuario(String nombre, String password, String email){
        //funcio per que en les proves on no es posa un id el codi no peti

    }

    public Usuario(){

     }

    public Usuario (String email, String password){

        this.setEmail(email);
        this.setPassword(password);

    }

    @Override
    public String toString(){
        return "Nombre: " + getNombre() + " Password: " + getPassword();
    }

    //Getters y Setters

    public int getObj1() {
        return Obj1;
    }

    public void setObj1(int obj1) {
        Obj1 = obj1;
    }

    public int getObj2() {
        return Obj2;
    }

    public void setObj2(int obj2) {
        Obj2 = obj2;
    }

    public int getObj3() {
        return Obj3;
    }

    public void setObj3(int obj3) {
        Obj3 = obj3;
    }

    public int getObj4() {
        return Obj4;
    }

    public void setObj4(int obj4) {
        Obj4 = obj4;
    }

    public int getUltimaposicion() {
        return ultimaposicion;
    }

    public void setUltimaposicion(int ultimaposicion) {
        this.ultimaposicion = ultimaposicion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
