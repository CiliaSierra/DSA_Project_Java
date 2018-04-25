package celdas;

public class Puerta extends Celda {

    Estado estado;
    enum Estado{Abierta, Cerrada}

    public String getLetra() {
        return "P";
    }
}
