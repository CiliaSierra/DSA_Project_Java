package server;

import API.MundoImpl;
import banco.BancoImpl;
import jdk.nashorn.internal.objects.annotations.Getter;
import jugador.Objeto;
import jugador.Usuario;
import org.json.JSONObject;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @GET //http://localhost:8080/myapp/funciones/Hello
    @Path("/Hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        men = "Hello ";
        return men;
    }

    //FUNCIONES de MundoInterfaz
    @POST
    @Path("/consultarUsuario") //LOGIN
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass) {
       String password = mundoImpl.consultarUsuario(user);
      if (pass.equals(password) )//consulta al dao null point exception
          return Response.status(200).build();//Login Correcte;
      else
          return Response.status(403).build();//Login Incorrecte (las contraseñas no coiciden)

    }

    @POST
    @Path("/crearUsuario") //REGISTRARSE
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass/*,@QueryParam("pass2") String pass2*/, @QueryParam("email") String email) {
      // if (pass.equals(pass2)) {
           Usuario u = new Usuario(user, pass, email);
           if (mundoImpl.crearUsuario(u))
               return Response.status(201).build();//Register realizado correcte
           else
               return Response.status(403).build();//Register realizado incorrecte
      // }
     //  else
        //   return Response.status(400).build();//Las contraseñas no coiciden
    }

    //Exemple per retornar una resposta

    @POST      //Cal canviar a post
    @Path("/cambiarPass") //CAMBIAR PASS
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarPass(@QueryParam("user") String user,@QueryParam("pass") String pass, @QueryParam("pass2") String pass2){
        if (pass.equals(pass2)){
            if( mundoImpl.cambiarPass(user,pass))
                return Response.status(200).build();//Contraseña cambiada adecuadamente
            else
                return Response.status(403).build();//Error al cambiar contraseña
        }
        else
            return Response.status(400).build();//las contraseñas no coiciden
    }

    @DELETE
    @Path("/eliminarUsuario") //ELIMINAR USER
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@QueryParam("user") String user, @QueryParam("pass") String pass) {
        if (pass.equals(mundoImpl.consultarUsuario(user))) {
            if (mundoImpl.eliminarUsuario(user))
                return Response.status(200).build();//User eliminado con exito
            else
                return Response.status(403).build();//user no se ha podido eliminar
        }

        else
           return Response.status(400).build();//la contaseña enviada no concuerda con la del user
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

