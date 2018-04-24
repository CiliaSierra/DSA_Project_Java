package jugador;

public class Pack {

    private int cantidad;
    private Objeto objeto;

    public Pack(int cantidad, Objeto objeto){
        this.setCantidad(cantidad);
        this.setObjeto(objeto);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int increaseCantidad(){
        return ++this.cantidad;
    }

    public int decreaseCantidad(){
        return --this.cantidad;
    }

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }
}
