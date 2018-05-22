package server;

import API.MundoImpl;
import celdas.Mapa;
import celdas.Views;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @Path("/getbyname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMap(@PathParam("name") String nombremapa){
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
