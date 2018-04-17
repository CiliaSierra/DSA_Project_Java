package src;

public class Cuenta extends Banco {
    private String titular;
    private int saldo;

    public Cuenta(String nombre, int altura, int anchura) {
        super(nombre, altura, anchura);
    }

    public String getTitular() {
        return titular;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
