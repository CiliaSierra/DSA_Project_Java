package banco;

public class Cuenta extends BancoImpl { //por cada cliente una cuenta
    private String titular;
    private int saldo;

    public Cuenta(String nombre, int altura, int anchura) {
        super(nombre, altura, anchura);
    }

   public Cuenta(String titular, int saldo){
        this.titular = titular;
        this.saldo = saldo;
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

    public void afegirSaldo(int quantitat){
        this.saldo += quantitat;
    }

    public void treureSaldo(int quantitat){this.saldo -= quantitat;}

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
