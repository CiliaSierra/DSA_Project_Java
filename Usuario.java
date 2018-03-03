import java.util.LinkedList;

public class Usuario {

    private String nombre;
    private String password;
    private int nivel;
    private int ataque;
    private int defensa;

    public LinkedList<Objeto> inventario = new LinkedList<Objeto>();

    public Usuario(String nombre, String password, int nivel, int ataque, int defensa){
        this.setNombre(nombre);
        this.setPassword(password);
        this.setNivel(nivel);
        this.setAtaque(ataque);
        this.setDefensa(defensa);
    }

    @Override
    public String toString(){
        return "Nombre: " + getNombre() + " Password: " + getPassword() + " Nivel: " + getNivel() + " Ataque: " + getAtaque() + " Defensa: " + getDefensa();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}
