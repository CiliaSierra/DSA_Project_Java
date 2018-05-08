package API;

import org.apache.log4j.Logger;

public class MundoUsuario {

    final static Logger logger = Logger.getLogger(MundoUsuario.class);
    private static MundoUsuario instance = null;

    public static MundoUsuario getInstance() {
        if (instance == null) instance = new MundoUsuario();
        return instance;
    }

}
