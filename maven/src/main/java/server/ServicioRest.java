package server;

import API.MundoImpl;
import banco.BancoImpl;
import jugador.Usuario;


import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/funciones")
@Singleton //Lo necesitamos para decirle a jerser que use una unica instancia

public class ServicioRest {
    private MundoImpl  mundoImpl;
    public ServicioRest(){ mundoImpl = API.MundoImpl.getInstance();}

    private BancoImpl bancoImpl =  API.Singleton.getInstance().getBancoImpl();


    //Testing purposes "/Hello"
    @GET //http://localhost:8080/myapp/funciones/Hello
    @Path("/Hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "HELLO";
    }

    //FUNCIONES de MundoInterfaz
    @POST
    @Path("/consultarUsuario") //LOGIN
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuario(@FormParam("user") String user, @FormParam("pass") String pass) throws Exception {
        Usuario usuario = new Usuario(user,pass);
        //String password = mundoImpl.consultarUsuario(user);
      if (mundoImpl.loginBool(usuario))//consulta al dao null point exception   pass.equals("pass")
          return Response.status(200).build();//Login Correcte;
      else
          return Response.status(403).build();//Login Incorrecte (las contraseñas no coiciden)

    }

    @POST
    @Path("/crearUsuario") //REGISTRARSE
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuario(@FormParam("user") String user, @FormParam("pass") String pass,@FormParam("pass2") String pass2, @FormParam("email") String email) throws Exception { Usuario u = new Usuario(user, pass, email);
           if (mundoImpl.crearUsuario(u)) {
               mundoImpl.register(u);
               return Response.status(200).build();//Register realizado correcte
           }
           else
               return Response.status(403).build();//Register realizado incorrecte
    }

    @POST
    @Path("/cambiarPass") //CAMBIAR PASS
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarPass(@FormParam("user") String user,@FormParam("pass") String pass, @FormParam("pass2") String pass2){
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
    public Response eliminarUsuario(@FormParam("user") String user, @FormParam("pass") String pass) throws Exception {
        Usuario usuario = new Usuario(user,pass);
        if (mundoImpl.eliminarUsuario(usuario)) {
                mundoImpl.deleteUser(usuario);
                return Response.status(200).build();//User eliminado con exito
            }
            else
                return Response.status(403).build();//user no se ha podido eliminar o por que no exixte o porque la contraseña es incorrecta

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
    public Boolean guardarMonedas(@FormParam("id") int monedas, @FormParam("titular") String titular) {
        return bancoImpl.guardarMonedas(monedas, titular);
    }

    @POST
    @Path("/sacarMonedas")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean sacarMonedas(@FormParam("id") int monedas, @FormParam("titular") String titular) {
        return bancoImpl.sacarMonedas(monedas, titular);
    }

    @POST
    @Path("/crearCuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean crearCuenta(@FormParam("titular") String titular) {
        return bancoImpl.crearCuenta(titular);
    }
}

