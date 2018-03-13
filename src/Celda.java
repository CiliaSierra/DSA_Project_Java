package src;

public abstract class Celda {

    public abstract String letra ();

    public String toJSON(){
        return "{\"nombre\":\""+this.getClass().getSimpleName() +"\"}";
    }

}
