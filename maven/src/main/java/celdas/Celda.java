package celdas;


/*@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Arbusto.class, name = "Arbusto"),
    @JsonSubTypes.Type(value = Muro.class, name = "Muro"),
}
)*/
public abstract class Celda {

    public abstract String getLetra();

    public abstract String getNombre();

}
