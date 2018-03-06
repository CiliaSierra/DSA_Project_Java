package src;

import java.util.LinkedList;
import java.util.List;


public class Usuario {

    private String nombre;
    private String password;
    public String email;
    private int nivel;
    private int monedas;

    private LinkedList<Pack> inventario = new LinkedList<Pack>();
    private static final int invSize = 10;  //Tamaño del inventario
    private static final int packCant = 30; //Cantidad de objeto maxima en pack

    public Usuario(String nombre, String password, String email, int nivel){
        this.setNombre(nombre);
        this.setPassword(password);
        this.setNivel(nivel);
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
                if(pack.objeto == obj)
                    if(pack.cantidad<packCant) {
                        pack.cantidad++;
                        return true;
                    }
            }
        }
        return false;
    }
    public boolean invRemove(Objeto obj){
        for(Pack pack : inventario){
             if(pack.objeto == obj){
                 pack.cantidad--;
                 if(pack.cantidad== 0)
                     inventario.remove(obj);
                 return true;
                }
            }
        return false;
    }
    public boolean contains(Object obj){
        for(Pack pack : inventario){
            if(pack.objeto==obj)
                return true;
        }
        return false;
    } //Comprobar si el objeto esta en el invetario
    public List<Pack> getInventario(){
        return this.inventario;
    }
}
