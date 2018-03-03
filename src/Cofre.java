package src;

public class Cofre extends Celda {


    Tipo tipo;
    enum Tipo {oro, plata, cobre}

    public String letra() {
        return "$";
    }
}
