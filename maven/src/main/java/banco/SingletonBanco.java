package banco;


import java.util.ArrayDeque;
import java.util.ArrayList;

public class SingletonBanco {

    private static SingletonBanco instance; //Declaro la instancia

    /*private Singleton() {//Constructor del singleton DEBE SER PRIVADO
        // Exists only to defeat instantiation.
    }*/

    public static SingletonBanco getInstance() {//Constructor del singleton DEBE SER PRIVADO
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
