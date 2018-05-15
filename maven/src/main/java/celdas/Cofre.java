package celdas;

import com.fasterxml.jackson.annotation.JsonView;

public class Cofre extends Celda {

    @JsonView(Views.Normal.class)
    private Tipo tipo;

    @JsonView(Views.Normal.class)
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public enum Tipo {oro, plata, cobre}

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetra() {
        return "$";
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Cofre";
    }

    public Cofre(Tipo tipo){
        this.setTipo(tipo);
    }

    public Cofre(){}
}
