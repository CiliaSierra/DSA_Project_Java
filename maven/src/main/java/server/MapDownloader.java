package server;

import API.MundoImpl;
import celdas.Mapa;
import celdas.Views;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/map")
public class MapDownloader {

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getList(){
        List<Mapa> mapaList = MundoImpl.getInstance().getMapas();
        JSONArray ja = new JSONArray();
        for(Mapa m : mapaList){
            ja.put(m.getNombre());
        }
        return ja.toString();
    }

    @GET
    @Path("/getbyname")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getMap(@QueryParam("nombremapa") String nombremapa){
        Mapa m = Mapa.cargarMapa(nombremapa);
        ObjectMapper om = new ObjectMapper();
        String result;
        try {
            result = om.writerWithView(Views.Normal.class).writeValueAsString(m);
        } catch (JsonProcessingException e) {
            result = "";
        }
        return result;
    }
    
}
