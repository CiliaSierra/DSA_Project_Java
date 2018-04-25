package celdas;

public abstract class Celda {

    public abstract String getLetra();

    public String toJSON(){
        return "{\"nombre\":\""+this.getClass().getSimpleName() +"\"}";
    }

}
