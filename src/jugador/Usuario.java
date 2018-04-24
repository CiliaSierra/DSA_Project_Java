package jugador;

import java.util.LinkedList;
import java.util.List;


public class Usuario {

    private String nombre;
    private String password;
    private String email;
    private int nivel;
    private int monedas;

    private LinkedList<Pack> inventario = new LinkedList<Pack>();
    private static final int invSize = 10;  //Tamaño del inventario
    private static final int packCant = 30; //Cantidad de objeto maxima en pack

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
    public boolean invAdd(Objeto obj){
        if(!contains(obj)){
            if (inventario.size() < invSize) {
                inventario.add(new Pack(1, obj));
                return true;
            }
        }
        else {
            for(Pack pack : inventario){
                if(pack.getObjeto() == obj)
                    if(pack.getCantidad()<packCant) {
                        pack.increaseCantidad();
                        return true;
                    }
            }
        }
        return false;
    }
    public boolean invRemove(Objeto obj){
        for(Pack pack : inventario){
             if(pack.getObjeto() == obj){
                 pack.decreaseCantidad();
                 if(pack.getCantidad() == 0)
                     inventario.remove(obj);
                 return true;
                }
            }
        return false;
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
}
