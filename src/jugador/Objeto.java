package jugador;

public class Objeto {

    private String nombre;
    private String tipo;
    private String descripcion;


    public Objeto(String nombre, String tipo, String descripcion){
        this.setNombre(nombre);
        this.setTipo(tipo);
        this.setDescripcion(descripcion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
