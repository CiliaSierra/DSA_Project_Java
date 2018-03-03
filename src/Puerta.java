package src;

public class Puerta extends Celda {

    Estado estado;
    enum Estado{Abierta, Cerrada}

    public String letra() {
        return "P";
    }
}
