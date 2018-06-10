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
    @Path("/consultarUsuario")
    @Produces(MediaType.TEXT_PLAIN)
    public String consultarUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass) {

        String password = mundoImpl.consultarUsuario(user);

      if (user.equals("user") && pass.equals("123") )//consulta al dao null point exception
            men = "logeado correctamnte";
      else
          men = "Contenido erroneo";

        return men;
    }

    @GET
    @Path("/crearUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean crearUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass,@QueryParam("email") String email) {
        Usuario u = new Usuario(user,pass,email);
        return mundoImpl.crearUsuario(u);
    }

    @POST
    @Path("/eliminarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean eliminarUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass) {
        //comprobar contraseña
        return mundoImpl.eliminarUsuario(user);
    }


    @GET
    @Path("/listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listaUsuarios () {
        return mundoImpl.listaUsuarios();
    }

    @GET
    @Path("/listaObjetos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> listaObjetos (@QueryParam("user") String user) {
        return mundoImpl.listaObjetos(user);
    }

    /*@POST
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
    public int saldo(@FormParam("titular") String titular) {
        return bancoImpl.saldo(titular);
    }

    @POST
    @Path("/guardarMonedas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean guardarMonedas(@QueryParam("id") int monedas, @FormParam("titular") String titular) {
        return bancoImpl.guardarMonedas(monedas, titular);
    }

    @POST
    @Path("/sacarMonedas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean sacarMonedas(@QueryParam("id") int monedas, @FormParam("titular") String titular) {
        return bancoImpl.sacarMonedas(monedas, titular);
    }

    @POST
    @Path("/crearCuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean crearCuenta(@FormParam("titular") String titular) {
        return bancoImpl.crearCuenta(titular);
    }
}

