package jugador;

import celdas.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class Usuario {

    @JsonView(Views.Normal.class)
    private String nombre;
    @JsonView(Views.Normal.class)
    private String password;
    @JsonView(Views.Normal.class)
    private String email;
    @JsonView(Views.Normal.class)
    private int nivel;
    @JsonView(Views.Normal.class)
    private int monedas;

    @JsonView(Views.NotNormal.class)
    private LinkedList<Pack> inventario = new LinkedList<>();
    @JsonView(Views.NotNormal.class)
    public static final int inventorioMaxSize = 10;  //Tamaño del inventario
    @JsonView(Views.NotNormal.class)
    public static final int packMaxQuantity = 30; //Cantidad de objeto maxima en un pack


    //Constructores

    public Usuario(String nombre, String password, String email, int nivel, int monedas){
        this.setNombre(nombre);
        this.setPassword(password);
        this.setEmail(email);
        this.setNivel(nivel);
        this.setMonedas(monedas);
    }

    public Usuario(){
        this.inventario = new LinkedList<>();
    }
    @Override
    public String toString(){
        return "Nombre: " + getNombre() + " Password: " + getPassword() + " Nivel: " + getNivel();
    }

    //Getters y Setters
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
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getMonedas() {
        return monedas;
    }
    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }



    //Controlar Inventario
    public int invAdd(Objeto objeto, int cantidad){
        if(!contains(objeto)){
            if (inventario.size() < inventorioMaxSize) {
                if(cantidad < packMaxQuantity){
                    inventario.add(new Pack(cantidad, objeto));
                    return cantidad;
                }
                else{
                    inventario.add(new Pack(packMaxQuantity, objeto));
                    return packMaxQuantity;
                }
            }
        }
        else {
            for(Pack pack : inventario) {
                if (pack.getObjeto() == objeto) {
                    if (pack.getCantidad() + cantidad > packMaxQuantity) {
                        pack.increaseCantidad(packMaxQuantity - pack.getCantidad());
                        return pack.getCantidad();
                    }
                    else{
                        pack.increaseCantidad(cantidad);
                        return cantidad;
                    }
                }
            }
        }
        return 0;
    }
    public int invRemove(Objeto objeto, int cantidad){
        int result = 0;
        for(Pack pack : inventario){
            if(pack.getObjeto() == objeto){
                result = pack.decreaseCantidad(cantidad);
                if(pack.getCantidad() == 0)
                    inventario.remove(objeto);
                return result;
            }
        }
        return result;
    }
    public boolean contains(Object obj){
        for(Pack pack : inventario){
            if(pack.getObjeto() == obj)
                return true;
        }
        return false;
    } //Comprobar si el objeto esta en el invetario
    public List<Pack> getInventario(){
        return this.inventario;
    }
    public int objectCount(){
        int i = 0;
        for(Pack pack : getInventario()){
            i += pack.getCantidad();
        }
        return i;
    }
}
