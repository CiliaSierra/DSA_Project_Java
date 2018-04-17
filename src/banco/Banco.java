package banco;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    List<Cuenta> cuentas = new ArrayList<>();


    //lista de clase de cuetas usuario,saldo
    public Banco(String nombre, int altura, int anchura) {

        //super(nombre, altura, anchura);


    } //constructor

    //mostostrar saldo
    public int saldo(String titular) {
        int saldo = 0;
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getTitular() == titular) {
                saldo = cuentas.get(i).getSaldo();
            }
        }
        return saldo;
    }

    //guardar monedas
    public boolean guardarMonedas(int monedas, String titular) {
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getTitular() == titular) {
                cuentas.get(i).afegirSaldo(monedas);
                return true;
            }
        }
        return false ;
    }

    //sacar monedas
    public boolean sacarMonedas(int monedas, String titular) { //Añadir excepcion de saldo a 0
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getTitular() == titular) {
                cuentas.get(i).treureSaldo(monedas);
                return true;
            }
        }
        return false;
    }

    //crear una cuenta
    public boolean crearCuenta(String titular){
        int posicion = cuentas.size();
        //comprobar que no hay dos titulares iguales
        for(Cuenta cuenta : cuentas) {
            if (cuenta.getTitular().equals(titular)){
                return false;
            }
        }
        cuentas.get(posicion).setTitular(titular);
        cuentas.get(posicion).setSaldo(0);
        return true;
    }
}


