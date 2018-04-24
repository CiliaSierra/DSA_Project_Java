import jugador.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


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

}
