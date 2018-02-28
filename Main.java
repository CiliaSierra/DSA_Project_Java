import java.util.List;
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
                choice = -1;
                e.printStackTrace();
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
                    break;
                }
                case 4:{
                    System.out.print("Nombre del jugador: ");
                    String nombre = scan.nextLine();
                    Usuario user = m.consultarUsuario(nombre);
                    if(user == null){
                        System.out.println("Usuario no existe");
                        break;
                    }
                    System.out.println("Tipo de objeto:");
                    System.out.println("1 Ataque");
                    System.out.println("2 Defensa");
                    System.out.println("3 Llave");
                    System.out.println("4 Pocion");
                    String option = scan.nextLine();
                    String tipo ="";
                    if(option == "1"){
                        tipo = "Ataque";
                    }
                    else if(option == "2"){
                        tipo = "Defensa";
                    }
                    else if(option == "3"){
                        tipo = "Llave";
                    }
                    else if(option == "4"){
                        tipo = "Pocion";
                    }
                    else{
                        System.out.println("Debes introducir un numero del 1 al 4.");
                        break;
                    }
                    System.out.print("Nombre del objeto: ");
                    String nom = scan.nextLine();
                    System.out.print("Añadir descripción a "+nom+": ");
                    String descripcion = scan.nextLine();
                    System.out.print("Valor del objeto (numero): ");
                    String value = scan.nextLine();
                    int val = 0;
                    try{
                        val = Integer.parseInt(value);
                    }
                    catch (Exception e){
                        System.out.println("Debes introducir un numero.");
                        break;
                    }
                    Objeto objeto = new Objeto(nom,tipo, descripcion,val);
                    m.añadirObjetoAUsuario(user,objeto);
                    System.out.println("Objeto " + objeto.nombre + ", añadido a "+user.nombre+".");
                    break;
                }
                case 5: {
                    System.out.print("Nombre del usuario: ");
                    String nombre = scan.nextLine();
                    Usuario user = m.consultarUsuario(nombre);
                    if(user == null){
                        System.out.println("Usuario no existe.");
                        break;
                    }
                    System.out.println("Objetos del usuario "+ user.nombre+":");
                    for(Objeto obj : user.inventario){
                        System.out.println(obj.nombre);
                    }
                    break;
                }
                case 6:{
                    break;
                }
                case 7:{
                    break;
                }
                case 8:{
                    break;
                }
            }
        }
    }
}
