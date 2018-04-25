package celdas;

public class Cofre extends Celda {


    private Tipo tipo;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public enum Tipo {oro, plata, cobre}

    public String getLetra() {
        return "$";
    }
    public Cofre(Tipo tipo){
        this.setTipo(tipo);
    }

    @Override
    public String toJSON(){
        return "{\"nombre\":\""+this.getClass().getSimpleName()+"\",\"tipo\":\""+ getTipo() +"\"}";
    }
}
