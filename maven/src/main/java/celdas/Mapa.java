package celdas;

import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class Mapa {

    @JsonView(Views.Normal.class)
    private Celda[][] celdas;

    @JsonView(Views.Normal.class)
    private String nombre;

    @JsonView(Views.Normal.class)
    private int altura;

    @JsonView(Views.Normal.class)
    private int anchura;

    @JsonView(Views.NotNormal.class)
    private Logger logger = Logger.getLogger(Mapa.class.getName());

    public Mapa(String nombre, int altura, int anchura){
        this.setNombre(nombre);
        this.setCeldas(new Celda[altura][anchura]);
        this.setAltura(altura);
        this.setAnchura(anchura);
    }

    public boolean llenarMapa(List<Celda> celdasArg){
        if(getCeldas().length == getAltura() * getAnchura()){
            getLogger().warning("El número de celdas no coincide con las dimensiones del mapa");
            return false;
        }
        for(int i = 0; i< getAltura(); i++){
            for(int j = 0; j< getAnchura(); j++){
                getCeldas()[i][j] = celdasArg.get(i* getAnchura() +j);
            }
        }
        return true;
    }
    public void mostrarMapa(){
        for (Celda[] celda : getCeldas()) {
            for (int j = 0; j < getCeldas()[0].length; j++) {
                System.out.print(celda[j].getLetra());
            }
            System.out.println();
        }
    }

    public void guardarMapa() throws IOException {
        JSONObject mapa = new JSONObject();
        mapa.put("nombre", getNombre());
        mapa.put("altura", getAltura());
        mapa.put("anchura", getAnchura());

        JSONArray ja = new JSONArray();
        for (int i = 0; i < getCeldas().length; i++) {
            for (int j = 0; j < getCeldas()[0].length; j++) {
                ja.put(new JSONObject(getCeldas()[i][j].toJSON()));
            }
        }

        mapa.put("celdas", ja);
        PrintWriter pw = new PrintWriter(getNombre() +".txt");
        pw.write(mapa.toString());
        pw.close();
    }

    public void leerMapa() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(getNombre() +".txt"));
        JSONObject jo = new JSONObject(br.read());
        this.setNombre(jo.getString("nombre"));
        this.setAnchura(jo.getInt("altura"));
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public int getAnchura() {
        return anchura;
    }
    public void setAnchura(int anchura) {
        this.anchura = anchura;
    }
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}