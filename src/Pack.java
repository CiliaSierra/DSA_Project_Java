package banco;

public class Pack {

    private int cantidad;
    private banco.Objeto objeto;

    public Pack(int cantidad, banco.Objeto objeto){
        this.setCantidad(cantidad);
        this.setObjeto(objeto);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public banco.Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(banco.Objeto objeto) {
        this.objeto = objeto;
    }
}
