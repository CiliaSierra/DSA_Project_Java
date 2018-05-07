package API;

public class SingletonMundo {
    private static SingletonMundo instance;
    /*private Singleton() {
        // Exists only to defeat instantiation.
    }*/
    public static SingletonMundo getInstance() {
        if(instance == null) {
            instance = new SingletonMundo();
        }
        return instance;
    }

    /*private ProductManagerImpl impl = new ProductManagerImpl();

   // public ProductManagerImpl getImpl() {
        return impl;
    }*/
}
