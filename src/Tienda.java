package src;

import java.util.LinkedList;

public class Tienda extends Mapa{
    private int monedas;

    LinkedList<Pack> inventario = new LinkedList<Pack>();

    //creo el constructor de tienda
    public Tienda(String nombre, int altura, int anchura) {
        super(nombre, altura, anchura);
    }

    //creo los metodos
    public void comprar (int dinero, Objeto obj){
        Usuario u = new Usuario("Sara","1234","sara@gmail.com",4, 65);

        if (dinero < u.getMonedas()) {
            u.invAdd(obj); //al usuario le sumo el objeto
            u.setMonedas(u.getMonedas()-1); //al usuario le quito monedas
        }
    }
}
