package API;

import dao.DAOImpl;
import jugador.Usuario;
import org.apache.log4j.Logger;

public class MundoUsuario {

    final static Logger logger = Logger.getLogger(MundoUsuario.class);
    private static MundoUsuario instance = null;

    public static MundoUsuario getInstance() {
        if (instance == null) instance = new MundoUsuario();
        return instance;
    }


    public Usuario login(Usuario usuario) throws Exception {
        try {
            return DAOImpl.getInstance().selectUserByUsernameAndPw(usuario.getNombre(), usuario.getPassword());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
