import java.util.Scanner;

public class Main {

    public static Mundo m = new Mundo();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Menu principal de opciones:");
            System.out.println("1 : Añadir Usuario");
            System.out.println("2 : Eliminar Usuario");
            System.out.println("3 : Consultar Usuario");
            System.out.println("4 : Añadir objeto a un Usuario");
            System.out.println("5 : Consultar Objetos de un Usuario");
            System.out.println("6 : Consultar un Objeto de un Usuario");
            System.out.println("7 : Eliminar Objeto de un Usuario");
            System.out.println("8 : Tranferir un Objeto entre Usuarios");
            System.out.println("0 : Salir de la Aplicación");

            String input = scan.nextLine();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                choice = -1;
            }
            switch (choice) {
                case -1: {
                    System.out.println("You must enter a valid number");
                    break;
                }
                case 0: {
                    return;
                }
                case 1: {
                    System.out.print("Nombre: ");
                    String nombre = scan.nextLine();
                    System.out.print("Password: ");
                    String passwordd = scan.nextLine();
                    int nivel = 0;
                    int ataque = 0;
                    int defensa = 0;
                    while (true) {
                        try {
                            System.out.print("Nivel: ");
                            String lvl = scan.nextLine();
                            nivel = Integer.parseInt(lvl);
                            System.out.print("Ataque: ");
                            String att = scan.nextLine();
                            ataque = Integer.parseInt(att);
                            System.out.print("Defensa: ");
                            String def = scan.nextLine();
                            defensa = Integer.parseInt(def);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Usuario u = new Usuario(nombre, passwordd, nivel, ataque, defensa);
                    if (m.crearUsuario(u))
                        System.out.println("Usuario añadido.");
                    else
                        System.out.println("Usuario con nombre " + u.nombre + " ya existe.");
                    break;
                }
                case 2: {
                    System.out.print("Nombre de usuario a eliminar: ");
                    String nombre = scan.nextLine();
                    if(m.eliminarUsuario(nombre))
                        System.out.println("Usuario eliminado");
                    else
                        System.out.println("Usuario no existe");
                    break;
                }
                case 3:{
                    System.out.print("Nombre de usuario a consultar: ");
                    String nombre = scan.nextLine();
                    Usuario u = m.consultarUsuario(nombre);
                    if(u == null){
                        System.out.println("Usuario no existe");
                        break;
                    }
                    System.out.println(u.toString());
                }
                case 4:{

                }
            }
        }
    }
}
