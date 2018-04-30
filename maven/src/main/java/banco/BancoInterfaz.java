package banco;

public interface BancoInterfaz {
    //funciones del banco
    //mostostrar saldo
    int saldo(String titular);

    //guardar monedas
    boolean guardarMonedas(int monedas, String titular);

    //sacar monedas
    boolean sacarMonedas(int monedas, String titular);

    //crear una cuenta
    boolean crearCuenta(String titular);
}
