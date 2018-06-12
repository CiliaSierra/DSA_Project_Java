package server;

import API.MundoImpl;
import banco.BancoImpl;
import jdk.nashorn.internal.objects.annotations.Getter;
import jugador.Objeto;
import jugador.Usuario;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import static API.Singleton.getInstance;


@Path("/funciones")
@Singleton //Lo necesitamos para decirle a jerser que use una unica instancia

public class ServicioRest {
    protected MundoImpl  mundoImpl;
    public ServicioRest(){ mundoImpl = API.MundoImpl.getInstance();};
    BancoImpl bancoImpl =  API.Singleton.getInstance().getBancoImpl();
    String men; //alamcenamiento de la respuesta al la web

    //Testing purposes "/Hello"
    @GET
    @Path("/Hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        men = "Hello ";
        return men;
    }

    //FUNCIONES de MundoInterfaz
    @GET
    @Path("/consultarUsuario") //LOGUIN
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass) {
       // String password = mundoImpl.consultarUsuario(user);
      if (user.equals("user") && pass.equals("123") )//consulta al dao null point exception
            men = "logeado correctamnte";
      else
          men = "Contenido erroneo";

        return men;
    }

    @GET
    @Path("/crearUsuario") //REGISTRARSE
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean crearUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass,@QueryParam("email") String email) {
        Usuario u = new Usuario(user,pass,email);
        return mundoImpl.crearUsuario(u);
    }

    @GET
    @Path("/cambiarPass") //CAMBIAR PASS
    @Produces(MediaType.APPLICATION_JSON)
    public String cambiarPass(@QueryParam("user") String user,@QueryParam("pass") String pass){
        return mundoImpl.cambiarPass(user,pass);
    }
    @POST
    @Path("/eliminarUsuario") //ELIMINAR USER
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean eliminarUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass) {
        if (pass.equals(mundoImpl.consultarUsuario(user))) {
            return mundoImpl.eliminarUsuario(user);
        }
        else
            return false;
    }

    @GET
    @Path("/listaUsuarios") //LIST DE USER
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listaUsuarios () {
        return mundoImpl.listaUsuarios();
    }

    @GET
    @Path("/listaObjetos") //LIST OBJET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> listaObjetos (@QueryParam("user") String user) {
        return mundoImpl.listaObjetos(user);
    }
    /*
    @POST
    @Path("/añadirObjetoAUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public int añadirObjetoAUsuario(Usuario u, Objeto o, int cantidad) {
        return mundoImpl.añadirObjetoAUsuario(u, o, cantidad);
    }

    @POST
    @Path("/eliminarObjetoDeUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public int eliminarObjetoDeUsuario(Usuario u, String nombreObjeto, int cantidad) {
        return mundoImpl.eliminarObjetoDeUsuario(u, nombreObjeto, cantidad);
    }

    @GET
    @Path("/objFromNombre")
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto objFromNombre(Usuario u, String nombreObjeto) {
        return mundoImpl.objFromNombre(u, nombreObjeto);
    }*/

    //FUNCIONES  de BancoInterfaz
    @POST
    @Path("/saldo")
    @Consumes(MediaType.APPLICATION_JSON)
    public int saldo(@QueryParam("titular") String titular) {
        return bancoImpl.saldo(titular);
    }

    @POST
    @Path("/guardarMonedas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean guardarMonedas(@QueryParam("id") int monedas, @QueryParam("titular") String titular) {
        return bancoImpl.guardarMonedas(monedas, titular);
    }

    @POST
    @Path("/sacarMonedas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean sacarMonedas(@QueryParam("id") int monedas, @QueryParam("titular") String titular) {
        return bancoImpl.sacarMonedas(monedas, titular);
    }

    @POST
    @Path("/crearCuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean crearCuenta(@QueryParam("titular") String titular) {
        return bancoImpl.crearCuenta(titular);
    }
}

