package API;

import banco.BancoImpl;
import org.apache.log4j.Logger;

public class Singleton {

    final static Logger logger = Logger.getLogger(Singleton.class);
    private static Singleton instance = null;

    private Singleton() {
        // Exists only to defeat instantiation.
    }

    public static Singleton getInstance() { //Constructor del singleton DEBE SER PRIVADO
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }


    //Todos los que van a utilizar este singleton PRIVADOS
    private MundoImpl mundoImpl = MundoImpl.getInstance();
    private BancoImpl bancoImpl = BancoImpl.getInstance();

    //Getters de los que vana utilizar este singleton
    public MundoImpl getMundoImpl() {
        return mundoImpl;
    }
    public BancoImpl getBancoImpl() {
        return bancoImpl;
    }

}
