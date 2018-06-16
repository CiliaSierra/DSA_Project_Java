import API.MundoImpl;
import jugador.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class TestUsuario {
    private final static Logger logger = Logger.getLogger(String.valueOf(TestBanco.class));
    //Inicializo las variables para el test
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;

    private MundoImpl mundo;

    @Before
    public void setUp() throws Exception {
        mundo = MundoImpl.getInstance();
        usuario1 = new Usuario(2,"Carlos", "123456789", "carlos@gmail.com", "imagen1",2);
        usuario2 = new Usuario(3,"Pedro", "123456789", "pedro@gmail.com","imagen2",3 );
        usuario3 = new Usuario(4,"Martina", "123456789", "martina@gmail.com","imagen3",1);

        mundo.crearUsuario(usuario1);
        mundo.crearUsuario(usuario2);

    }

    @After
    public void tearDown(){
        usuario1 = null;
        usuario2 = null;
        mundo = null;
    }

    @Test
    public void crearUsuario() throws Exception {
        logger.info("Test: Creando usuario");
        boolean res = mundo.crearUsuario(usuario3);
        Assert.assertEquals(true,res);
    }

    @Test
    public void eliminarUsuario(){
        logger.info("Test: Eliminando usuario");
        boolean res = mundo.eliminarUsuario("Pedro");
        Assert.assertEquals(true,res);
    }

    @Test
    public void consultarUsuario(){
        logger.info("Test: Consultando usuario");
        String res = mundo.consultarUsuario("Pedro");
        Assert.assertEquals("123456789",res);
    }

    @Test
    public void login(){
        logger.info("Test: Log in usuario");
        boolean res = mundo.logInSara("Pedro", "123456789");
        Assert.assertEquals(true,res);
    }

    @Test
    public void cambiarPass(){
        logger.info("Test: Cambiando password usuario");
        Boolean res = mundo.cambiarPass("Carlos", "123456789");
        Assert.assertEquals(false,res);
    }

    @Test
    public void cambiarPass2(){
        logger.info("Test: Cambiando password usuario");
        Boolean res = mundo.cambiarPass("Pedro", "123");
        Assert.assertEquals(true,res);
    }

    @Test
    public void cambiarPass3(){
        logger.info("Test: Cambiando password usuario");
        Boolean res = mundo.cambiarPass("Sara", "123456789");
        Assert.assertEquals(false, res);
    }

}
