import API.MundoImpl;
import banco.BancoImpl;

public class Singleton {
    private static Singleton instance;

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
    private MundoImpl mundoImpl = new MundoImpl();
    private BancoImpl bancoImpl = new BancoImpl();

    //Getters de los que vana utilizar este singleton
    public MundoImpl getMundoImpl() {
        return mundoImpl;
    }
    public BancoImpl getBancoImpl() {
        return bancoImpl;
    }

}
