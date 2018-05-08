package API;

public class SingletonMundo {

    private static SingletonMundo instance;

    private Singleton() {
        // Exists only to defeat instantiation.
    }

    public static SingletonMundo getInstance() { //Constructor del singleton DEBE SER PRIVADO
        if(instance == null) {
            instance = new SingletonMundo();
        }
        return instance;
    }

    private MundoImpl impl = new MundoImpl();

    public MundoImpl getImpl() {
        return impl;
    }
}
