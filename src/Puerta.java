package src;

public class Puerta extends Celda {

    Estado estado;
    enum Estado{Abierta, Cerrada}

    @Override
    public String letra() {
        return "P";
    }
}
