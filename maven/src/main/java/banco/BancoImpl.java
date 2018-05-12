package banco;

import banco.BancoInterfaz;

import java.util.ArrayList;
import java.util.List;

public class BancoImpl implements BancoInterfaz {
    List<Cuenta> cuentas = new ArrayList<>();
    private static BancoImpl bancoImpl;

    public static BancoImpl getInstance(){
        if(bancoImpl == null)
            return new BancoImpl();
        else
            return bancoImpl;
    }

    private BancoImpl(){}

    public BancoImpl(List<banco.Cuenta> cuentas){
        this.cuentas = cuentas;
    }

    //lista de clase de cuetas usuario,saldo
    public BancoImpl(String nombre, int altura, int anchura) {

        //super(nombre, altura, anchura);


    } //constructor

    //mostostrar saldo
    public int saldo(String titular) {
        int saldo = 0;
        for (banco.Cuenta cuenta : cuentas) {
            if (cuenta.getTitular() == titular) {
                saldo = cuenta.getSaldo();
            }
        }
        return saldo;
    }

    //guardar monedas
    public boolean guardarMonedas(int monedas, String titular) {
        for (banco.Cuenta cuenta : cuentas) {
            if (cuenta.getTitular() == titular) {
                cuenta.afegirSaldo(monedas);
                return true;
            }
        }
        return false ;
    }

    //sacar monedas
    public boolean sacarMonedas(int monedas, String titular) { //Añadir excepcion de saldo a 0
        for (banco.Cuenta cuenta : cuentas) {
            if (cuenta.getTitular() == titular) {
                cuenta.treureSaldo(monedas);
                return true;
            }
        }
        return false;
    }

    //crear una cuenta sin saldo
    public boolean crearCuenta(String titular){
        return crearCuenta(titular,0);
    }
    //crear cuenta con saldo
    public boolean crearCuenta(String titular, int saldo){
        //comprobar que no hay dos titulares iguales
        for(banco.Cuenta cuenta : cuentas) {
            if (cuenta.getTitular().equals(titular)){
                return false;
            }
        }
        cuentas.add(new Cuenta(titular,0));
        return true;
    }
}


