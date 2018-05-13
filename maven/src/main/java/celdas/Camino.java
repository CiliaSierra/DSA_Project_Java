package celdas;

import com.fasterxml.jackson.annotation.JsonView;

public class Camino extends Celda {

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetra()
    {
        return "c";
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Camino";
    }

}
