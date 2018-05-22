package server;

import API.MundoImpl;
import banco.BancoImpl;
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

    MundoImpl mundoImpl = API.Singleton.getInstance().getMundoImpl();
    BancoImpl bancoImpl =  API.Singleton.getInstance().getBancoImpl();

    //Testing purposes "/Hello"
    @GET
    @Path("/Hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    //FUNCIONES de MundoInterfaz
    @POST
    @Path("/userLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario login(Usuario u) {
        Usuario us = null;
        try {
            us = mundoImpl.login(u);
            return us;
        } catch (Exception e) {

            return null;
        }
    }

    @POST
    @Path("/crearUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean crearUsuario(Usuario u) {
        return mundoImpl.crearUsuario(u);
    }

    @POST
    @Path("/eliminarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean eliminarUsuario(String nombre) {
        return mundoImpl.eliminarUsuario(nombre);
    }

    @GET
    @Path("/consultarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario consultarUsuario(String nombre) {
        return mundoImpl.consultarUsuario(nombre);
    }

    @GET
    @Path("/listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listaUsuarios () {
        return mundoImpl.listaUsuarios();
    }

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
    }

    //FUNCIONES  de BancoInterfaz
    @GET
    @Path("/saldo")
    @Produces(MediaType.APPLICATION_JSON)
    public  int saldo(String titular) {
        return bancoImpl.saldo(titular);
    }

    @POST
    @Path("/{id}/guardarMonedas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean guardarMonedas(@PathParam("id") int monedas, @FormParam("titular") String titular) {
        return bancoImpl.guardarMonedas(monedas, titular);
    }

    @POST
    @Path("/{id}/sacarMonedas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean sacarMonedas(@PathParam("id") int monedas, @FormParam("titular") String titular) {
        return bancoImpl.sacarMonedas(monedas, titular);
    }

    @POST
    @Path("/crearCuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean crearCuenta(@FormParam("titular") String titular) {
        return bancoImpl.crearCuenta(titular);
    }
}

