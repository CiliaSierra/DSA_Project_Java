package src;
import java.util.ArrayList;
import java.util.List;

public class Banco extends Mapa{
    private int monedas;

    List<Cuenta> cuentas = new ArrayList<>();
    //lista de clase de cuetas usuario,saldo
    public Banco(String nombre, int altura, int anchura) {
        super(nombre, altura, anchura);
    }
    //mostostrar saldo

    //guardar monedas
    public void guardarMonedas(int monedas, String titular){
        for (int i=0; i<cuentas.size(); i++){
            if (cuentas.get(i).getTitular() == titular) {
                cuentas.get(i).getSaldo()=monedas;
            }
        }


    }
    //sacar monedas

}
