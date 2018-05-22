package celdas;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Mapa {

    @JsonView(Views.Normal.class)
    private ArrayList<ArrayList<Celda>> celdas = new ArrayList<ArrayList<Celda>>();

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
        this.setAltura(altura);
        this.setAnchura(anchura);
    }
    public Mapa(){}

    public void mostrarMapa(){
        for (List<Celda> celda : getCeldas()) {
            for (Celda c : celda) {
                System.out.print(c.getLetra());
            }
            System.out.println();
        }
    }

    public void guardarMapa() throws IOException {
        File f = new File("./maven/src/main/resources/Mapas/");
        if (!f.exists()) {
            f.createNewFile();
            logger.info("File with name: " + this.nombre + ".txt created for map.");
        }
        ObjectMapper om = new ObjectMapper();
        try {
            om.writerWithView(Views.Normal.class).writeValue(
                    new File("./maven/src/main/resources/Mapas/" + this.nombre + ".txt"), this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void cargar() throws IOException{
        ObjectMapper om = new ObjectMapper();
        SimpleModule sm = new SimpleModule("CeldaDeserializer", new Version(1,0,0,null,null,null));
        sm.addDeserializer(Celda.class, new CeldaDeserializer());
        om.registerModule(sm);
        Mapa m = om.readValue(new File("./maven/src/main/resources/Mapas/"+this.nombre+".txt"), Mapa.class);
        this.nombre = m.nombre;
        this.celdas = m.celdas;
        this.altura = m.altura;
        this.anchura = m.anchura;
    }

    public static Mapa cargarMapa(String nombreMapa){
        Mapa m = new Mapa(nombreMapa,0,0);
        try {
            m.cargar();
        } catch (IOException e) {
            return null;
        }
        return m;
    }

    public static List<Mapa> cargarMapas(){
        List<Mapa> result = new ArrayList<>();
        File f = new File("./maven/src/main/resources/Mapas/");
        for(File file : Objects.requireNonNull(f.listFiles())){
            String fileName = file.getName();
            fileName = fileName.substring(0,fileName.lastIndexOf('.'));
            result.add(Mapa.cargarMapa(fileName));
        }
        return result;
    }

    public void leerMapa() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(getNombre() +".txt"));
        JSONObject jo = new JSONObject(br.read());
        this.setNombre(jo.getString("nombre"));
        this.setAnchura(jo.getInt("altura"));
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

    public ArrayList<ArrayList<Celda>> getCeldas() {
        return celdas;
    }

    public void setCeldas(ArrayList<ArrayList<Celda>> celdas) {
        this.celdas = celdas;
    }
}
