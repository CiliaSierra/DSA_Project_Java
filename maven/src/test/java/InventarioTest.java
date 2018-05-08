import jugador.*;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;


public class InventarioTest {

    @Test
    public void addObject(){
        Usuario u = new Usuario("Sergi", "123456","sergi@gmail.com", 0, 0);
        Objeto o = new Objeto("Espada","Arma", "Afilada");
        u.invAdd(o,1);
        assertEquals(u.objectCount(),1);
        u.invAdd(o,1);
        assertEquals(u.objectCount(),2);
        assertEquals(u.getInventario().size(), 1);
        u.invRemove(o,2);
        assertEquals(u.objectCount(), 0);
    }

    @Test
    public void maxObject(){
        Usuario u = new Usuario("Sergi", "123456","sergi@gmail.com", 0, 0);
        Objeto o = new Objeto("Espada","Arma", "Afilada");
        u.invAdd(o,Usuario.packMaxQuantity +1);
        assertEquals(u.objectCount(),Usuario.packMaxQuantity);
    }

    @Test
    public void maxPack(){
        Usuario u = new Usuario("Sergi", "123456","sergi@gmail.com", 0, 0);
        ArrayList<Objeto> list = new ArrayList<>();
        for(int i = 0; i < Usuario.inventorioMaxSize+1; i++){
            list.add(new Objeto("Espada"+i,"Arma", "Afilada"));
        }
        assertEquals(u.objectCount(),0);
        for(int i = 0; i < list.size() - 1; i++){
            u.invAdd(list.get(i),1);
            assertEquals(u.objectCount(),i+1);
            assertEquals(u.getInventario().size(),i+1);
        }

        assertEquals(u.getInventario().size(),Usuario.inventorioMaxSize);
        int result = u.invAdd(list.get(list.size()-1),1);
        assertEquals(result,0);
        assertEquals(u.getInventario().size(),Usuario.inventorioMaxSize);

    }

}
