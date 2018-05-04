package banco;


public class SingletonBanco {
    private static SingletonBanco instance;
    private Singleton() {
        // Exists only to defeat instantiation.
    }
    public static SingletonBanco getInstance() {
        if(instance == null) {
            instance = new SingletonBanco();
        }
        return instance;
    }

    /*private ProductManagerImpl impl = new ProductManagerImpl();

   // public ProductManagerImpl getImpl() {
        return impl;
    }*/
}
