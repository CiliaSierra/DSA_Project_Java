package celdas;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class CeldaDeserializer extends StdDeserializer<Celda> {

    protected CeldaDeserializer(Class<?> vc) {
        super(vc);
    }

    public CeldaDeserializer(){
        this(null);
    }

    @Override
    public Celda deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectNode on = (ObjectNode) jsonParser.getCodec().readTree(jsonParser);
        String nombreCelda = on.findValue("nombre").asText();
        try {
            Class c = Class.forName("celdas." + nombreCelda);
            switch (nombreCelda) {
                case "Cofre":
                    String tipo = on.findValue("tipo").asText();
                    Field f = c.getDeclaredField("tipo");
                    Object o = Arrays.stream(f.getType().getDeclaredFields()).filter(x -> x.getName().equals(tipo))
                            .findFirst().get().get(f);
                    Celda cel = (Celda) c.getDeclaredConstructor(f.getType()).newInstance(o);
                    return cel;
                default:
                Constructor con = c.getDeclaredConstructor(null);
                return (Celda) con.newInstance(null);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
