package banco;

import jugador.Objeto;
import jugador.Pack;
import jugador.Usuario;

import java.util.LinkedList;


public class Tienda{

    private int monedas;

    LinkedList<Pack> inventario = new LinkedList<>();

    //creo el constructor de tienda
    public Tienda(){
    }

    //creo los metodos
    public void comprar (int dinero, Objeto obj){
        Usuario u = new Usuario("Sara","1234","sara@gmail.com",4, 65);

        if (dinero < u.getMonedas()) {
            u.invAdd(obj,1); //al usuario le sumo el objeto
            u.setMonedas(u.getMonedas()-1); //al usuario le quito monedas
        }
    }
}
