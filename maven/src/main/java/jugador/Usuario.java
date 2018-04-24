package jugador;

import java.util.LinkedList;
import java.util.List;


public class Usuario {

    private String nombre;
    private String password;
    private String email;
    private int nivel;
    private int monedas;

    private LinkedList<Pack> inventario = new LinkedList<>();
    public static final int inventorioMaxSize = 10;  //Tamaño del inventario
    public static final int packMaxQuantity = 30; //Cantidad de objeto maxima en un pack

    public Usuario(String nombre, String password, String email, int nivel, int monedas){
        this.setNombre(nombre);
        this.setPassword(password);
        this.setEmail(email);
        this.setNivel(nivel);
        this.setMonedas(monedas);
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    //Controlar Inventario
    @Deprecated
    public boolean invAdd(Objeto objeto){
        if(!contains(objeto)){
            if (inventario.size() < inventorioMaxSize) {
                inventario.add(new Pack(1, objeto));
                return true;
            }
        }
        else {
            for(Pack pack : inventario){
                if(pack.getObjeto() == objeto)
                    if(pack.getCantidad()< packMaxQuantity) {
                        pack.increaseCantidad();
                        return true;
                    }
            }
        }
        return false;
    }
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
    @Deprecated
    public boolean invRemove(Objeto objeto){
        for(Pack pack : inventario){
             if(pack.getObjeto() == objeto){
                 pack.decreaseCantidad();
                 if(pack.getCantidad() == 0)
                     inventario.remove(objeto);
                 return true;
                }
            }
        return false;
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
