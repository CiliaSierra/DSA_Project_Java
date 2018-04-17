package celdas;

public class Cofre extends Celda {


    Tipo tipo;
    public enum Tipo {oro, plata, cobre}

    public String letra() {
        return "$";
    }
    public Cofre(Tipo tipo){
        this.tipo = tipo;
    }

    @Override
    public String toJSON(){
        return "{\"nombre\":\""+this.getClass().getSimpleName()+"\",\"tipo\":\""+tipo+"\"}";
    }
}
