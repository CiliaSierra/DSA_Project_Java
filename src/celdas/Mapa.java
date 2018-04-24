package celdas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.List;
import java.util.logging.*;

public class Mapa {

    Celda[][] celdas;
    String nombre;
    int altura;
    int anchura;

    Logger logger = Logger.getLogger(Mapa.class.getName());

    public Mapa(String nombre, int altura, int anchura){
        this.nombre = nombre;
        this.celdas = new Celda[altura][anchura];
        this.altura = altura;
        this.anchura = anchura;
    }

    public boolean llenarMapa(List<Celda> celdasArg){
        if(celdas.length == altura*anchura){
            logger.warning("El número de celdas no coincide con las dimensiones del mapa");
            return false;
        }
        for(int i = 0; i<altura; i++){
            for(int j = 0; j<anchura; j++){
                celdas[i][j] = celdasArg.get(i*anchura+j);
            }
        }
        return true;
    }
    public void mostrarMapa(){
        for (Celda[] celda : celdas) {
            for (int j = 0; j < celdas[0].length; j++) {
                System.out.print(celda[j].letra());
            }
            System.out.println();
        }
    }

    public void guardarMapa() throws IOException {
        JSONObject mapa = new JSONObject();
        mapa.put("nombre", nombre);
        mapa.put("altura", altura);
        mapa.put("anchura", anchura);

        JSONArray ja = new JSONArray();
        for (Celda[] celda : celdas) {
            for (int j = 0; j < celdas[0].length; j++) {
                ja.put(new JSONObject(celda[j].toJSON()));
            }
        }

        mapa.put("celdas", ja);
        PrintWriter pw = new PrintWriter(nombre+".txt");
        pw.write(mapa.toString());
        pw.close();
    }

    public void leerMapa() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nombre+".txt"));
        JSONObject jo = new JSONObject(br.read());
        this.nombre = jo.getString("nombre");
        this.anchura = jo.getInt("altura");
    }
}
