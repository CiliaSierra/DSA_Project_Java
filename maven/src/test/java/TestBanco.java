package server;
import banco.Banco;
import banco.Cuenta;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TestBanco {
    private final static Logger logger = Logger.getLogger(String.valueOf(TestBanco.class));
    //Inicializo las variables para el test
    private Cuenta cuenta1;
    private Cuenta cuenta2;

    private List<Cuenta> cuentas;
    private Banco banco;

    @Before
    public void setUp(){
        cuenta1 = new Cuenta("USER1",100);
        cuenta2 = new Cuenta("USER2", 60);
        cuentas = new ArrayList<>();
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        banco = new Banco(cuentas);
    }
    @After
    public void tearDown(){
        cuenta1 = null;
        cuenta2 = null;
        cuentas = null;
        banco = null;
    }
    //testear crear cuenta, mostrar, sacar y guardar monedas
    @Test
    public void crearCuentaTest(){
        logger.info("Test: Cear cuenta de  " +cuenta1.getTitular());
        boolean res = banco.crearCuenta(cuenta1.getTitular());
        Assert.assertEquals(true,res);
    }
    @Test
    public void mostrarSaldoTest(){
        logger.info("Test:Mostar Saldo de la cuenta de" + cuenta2.getTitular());
        int res = banco.saldo(cuenta2.getTitular());
        Assert.assertEquals(60,res);
    }
    @Test
    public void sacarSaldoTest(){
        logger.info("Test: Sacar Saldo de la cuenta de" + cuenta2.getTitular());
        boolean res = banco.sacarMonedas(10,cuenta2.getTitular());
        Assert.assertEquals(true,res);
    }
    @Test
    public void guardarMonedaTest() {
        logger.info("Test: Guardar monedas en el saldo de ña cuenta" + cuenta1.getTitular());
        boolean res = banco.guardarMonedas(12, cuenta2.getTitular());
        Assert.assertEquals(true, res);
    }
}
