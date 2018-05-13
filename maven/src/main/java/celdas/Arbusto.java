package celdas;

import com.fasterxml.jackson.annotation.JsonView;

public class Arbusto extends Celda {

    @Override
    @JsonView(Views.NotNormal.class)
    public String getLetra()
    {
        return "a";
    }

    @Override
    @JsonView(Views.Normal.class)
    public String getNombre() {
        return "Arbusto";
    }
}
