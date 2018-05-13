package celdas;

import com.fasterxml.jackson.annotation.JsonView;

public class Muro extends Celda {

    private String letra = "M";

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetra() {
        return letra;
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Muro";
    }

    public void setLetra(String letra){this.letra = letra;}

    public Muro(){}
}
