package server;

import API.MundoImpl;
import banco.BancoImpl;
import jugador.Usuario;


import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/funciones")
@Singleton //Lo necesitamos para decirle a jerser que use una unica instancia

public class ServicioRest {
    protected MundoImpl  mundoImpl;
    String usuarioslog;
    public ServicioRest(){
        mundoImpl = API.MundoImpl.getInstance();
    }

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
    @Path("/consultarUsuario") //Login WEB
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuario(@FormParam("user") String user, @FormParam("pass") String pass) {
        Usuario usuario = new Usuario(user,pass);
        usuarioslog = user;
      if (mundoImpl.loginBool2(usuario))//consulta al dao null point exception   pass.equals("pass")
          return Response.status(200).build();//Login Correcte;
      else
          return Response.status(403).build();//Login Incorrecte (las contraseñas no coiciden)
    }

    @POST //Register Web
    @Path("/crearUsuario2")
    @Produces(MediaType.TEXT_PLAIN)
    public Response register2(@FormParam("user") String user, @FormParam("pass") String pass, @FormParam("pass2") String pass2,@FormParam("email") String email) throws Exception  {
        Usuario usuario = new Usuario(user,pass,email);
        usuarioslog=user;
        boolean result = mundoImpl.register(usuario);
        if (result) {
            return Response.status(201).build();//Register realizado correcte
        } else
            return Response.status(403).build();//Register realizado incorrecte

    }

    @POST //Register Android
    @Path("/crearUsuario")
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(Usuario usuario) throws Exception  {
        boolean result = mundoImpl.register(usuario);
        if (result) {
            return Response.status(201).entity(result).build();//Register realizado correcte
        } else
            return Response.status(409).entity(result).build();//Register realizado incorrecte

    }

    @POST
    @Path("/crearUsuario3") //Register SARA siempre true
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerSara (Usuario usuario) throws SQLException {
        boolean result = mundoImpl.registerSara(usuario);
        if (result)
            return Response.status(201).entity(result).build();//Register Correcte
        else
            return Response.status(409).entity(result).build();//Register Incorrecte
    }


    @POST // Cambiar Password Android
    @Path("/cambiarPass") //CAMBIAR PASS http://localhost:8080/myapp/funciones/cambiarPass?user=user&pass=123&pass2=123
    @Produces(MediaType.TEXT_PLAIN) // tiene que recibir un usuario con el mail original y la password nueva
    public Response cambiarPass(Usuario usuario) {
        Usuario u = usuario;
        boolean result = mundoImpl.cambiarPass(u);

            if( result)
                return Response.status(200).entity(result).build();//Contraseña cambiada adecuadamente
            else
                return Response.status(403).entity(result).build();//Error al cambiar contraseña

    }

    @POST // Cambiar Password Web
    @Path("/cambiarPass2") //CAMBIAR PASS http://localhost:8080/myapp/funciones/cambiarPass?user=user&pass=123&pass2=123
    @Produces(MediaType.TEXT_PLAIN) // tiene que recibir un usuario con el mail original y la password nueva
    public Response cambiarPass2 (@FormParam("user") String user, @FormParam("pass") String pass,@FormParam("email") String email) throws Exception {
        Usuario usuario = new Usuario(user,pass,email);
        boolean result = mundoImpl.cambiarPass(usuario);

        if( result)
            return Response.status(200).entity(result).build();//Contraseña cambiada adecuadamente
        else
            return Response.status(403).entity(result).build();//Error al cambiar contraseña

    }


    @POST //Eliminar usuario Web
    @Path("/eliminarUsuario2")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarUsuario2(@FormParam("user") String user, @FormParam("pass") String pass,@FormParam("email") String email) throws Exception  {
        Usuario usuario = new Usuario(user,pass,email);
        usuarioslog=user;
        boolean result = mundoImpl.eliminaruser(usuario);
        if (result) {
            return Response.status(201).build();//Register realizado correcte
        } else
            return Response.status(403).build();//Register realizado incorrecte

    }

    @POST //Eliminar usuario Android
    @Path("/eliminarUsuario")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarUsuario(Usuario usuario) throws Exception  {
        boolean result = mundoImpl.eliminaruser(usuario);
        if (result) {
            return Response.status(201).build();//Register realizado correcte
        } else
            return Response.status(403).build();//Register realizado incorrecte

    }


    @POST
    @Path("/infoUsuario2") //Info WEB
    @Produces(MediaType.APPLICATION_JSON)
    public Response infoUsuario2 (@FormParam("email") String email) {
        Usuario usuario = new Usuario(email);

        if (mundoImpl.infobool(usuario))
            return Response.status(200).build();
        else
            return Response.status(403).build();
    }


    @POST
    @Path("/infoUsuario") //Login Android
    @Produces(MediaType.TEXT_PLAIN)
    public Response infoUsuario (Usuario usuario) throws SQLException {

        boolean result = mundoImpl.infobool(usuario);
        if (result)
            return Response.status(200).entity(result).build();
        else
            return Response.status(403).entity(result).build();
    }




    @GET
    @Path("/listaUsuarios") //LIST DE USER
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listaUsuarios () {
        return mundoImpl.listaUsuario();
    }

    @POST
    @Path("/unUsuario") //LIST de un usuario si me llega un USER
    @Produces(MediaType.APPLICATION_JSON) //@Produces(MediaType.TEXT_PLAIN)
    public Usuario unUsuario (Usuario usuario) throws Exception {
        return mundoImpl.unUsuario(usuario);
    }

    /*@GET
    @Path("/unUsuario/{idemail}") //LIST de un usuario si me llega un USER
    @Produces(MediaType.APPLICATION_JSON) //@Produces(MediaType.TEXT_PLAIN)
    public Usuario unUsuario (@Path("idemail") String idemail) {
        return mundoImpl.unUsuario2(idemail);
    }*/


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

