package src;

import java.util.Scanner;

public class Main {

    public static src.Mundo m = new src.Mundo();

    public static void main(String[] args) {

        Celda c1=new src.Muro();
        Celda c2=new src.Muro();
        Celda c3=new src.Camino();
        Celda c4=new Rio();
        Mapa mapa = new Mapa(2,2);
        mapa.celdas[0][0] =c1;
        mapa.celdas[0][1] =c2;
        mapa.celdas[1][0] =c3;
        mapa.celdas[1][1] =c4;
        mapa.mostrarMapa();
    }

    public void antiguo_main(){{
        Scanner scan = new Scanner(System.in);
        while (true) {
            ///hola
            System.out.println("Menu principal de opciones:");
            System.out.println("1 : Añadir Usuario");
            System.out.println("2 : Eliminar Usuario");
            System.out.println("3 : Consultar Usuario");
            System.out.println("4 : Añadir objeto a un Usuario");
            System.out.println("5 : Consultar Objetos de un Usuario");
            System.out.println("6 : Consultar un src.Objeto de un Usuario");
            System.out.println("7 : Eliminar src.Objeto de un Usuario");
            System.out.println("8 : Tranferir un src.Objeto entre Usuarios");
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
                    src.Usuario u = new src.Usuario(nombre, passwordd, nivel, ataque, defensa);
                    if (m.crearUsuario(u))
                        System.out.println("Usuario añadido.");

                    else
                        System.out.println("Usuario con nombre " + u.getNombre() + " ya existe.");
                    scan.nextLine();
                    break;
                }
                case 2: {
                    System.out.print("Nombre de usuario a eliminar: ");
                    String nombre = scan.nextLine();
                    if (m.eliminarUsuario(nombre))
                        System.out.println("Usuario eliminado");
                    else
                        System.out.println("Usuario no existe");
                    scan.nextLine();
                    break;
                }
                case 3: {
                    System.out.print("Nombre de usuario a consultar: ");
                    String nombre = scan.nextLine();
                    src.Usuario u = m.consultarUsuario(nombre);
                    if (u == null) {
                        System.out.println("Usuario no existe");
                        scan.nextLine();
                        break;
                    }
                    System.out.println(u.toString());
                    scan.nextLine();
                    break;
                }
                case 4: {
                    System.out.print("Nombre del jugador: ");
                    String nombre = scan.nextLine();
                    src.Usuario user = m.consultarUsuario(nombre);
                    if (user == null) {
                        System.out.println("Usuario no existe");
                        scan.nextLine();
                        break;
                    }
                    System.out.println("Tipo de objeto:");
                    System.out.println("1 Ataque");
                    System.out.println("2 Defensa");
                    System.out.println("3 Llave");
                    System.out.println("4 Pocion");
                    String option = scan.nextLine();
                    String tipo = "";
                    if (option == "1") {
                        tipo = "Ataque";
                    } else if (option == "2") {
                        tipo = "Defensa";
                    } else if (option == "3") {
                        tipo = "Llave";
                    } else if (option == "4") {
                        tipo = "Pocion";
                    } else {
                        System.out.println("Debes introducir un numero del 1 al 4.");
                        scan.nextLine();
                        break;
                    }
                    System.out.print("Nombre del objeto: ");
                    String nom = scan.nextLine();
                    System.out.print("Añadir descripción a " + nom + ": ");
                    String descripcion = scan.nextLine();
                    System.out.print("Valor del objeto (numero): ");
                    String value = scan.nextLine();
                    int val = 0;
                    try {
                        val = Integer.parseInt(value);
                    } catch (Exception e) {
                        System.out.println("Debes introducir un numero.");
                        scan.nextLine();
                        break;
                    }
                    src.Objeto objeto = new src.Objeto(nom, tipo, descripcion, val);
                    m.añadirObjetoAUsuario(user, objeto);
                    System.out.println("src.Objeto " + objeto.nombre + ", añadido a " + user.getNombre() + ".");
                    scan.nextLine();
                    break;
                }
                case 5: {
                    System.out.print("Nombre del usuario: ");
                    String nombre = scan.nextLine();
                    src.Usuario user = m.consultarUsuario(nombre);
                    if (user == null) {
                        System.out.println("Usuario no existe.");
                        break;
                    }
                    System.out.println("Objetos del usuario " + user.getNombre() + ":");
                    for (src.Objeto obj : user.inventario) {
                        System.out.println(obj.nombre);
                    }
                    break;
                }
                case 6: {
                    System.out.println();
                    break;
                }
                case 7: {
                    System.out.print("Nombre del usuario: ");
                    String nombre = scan.nextLine();
                    src.Usuario user = m.consultarUsuario(nombre);
                    if (user == null) {
                        System.out.println("No existe el usuario " + nombre);
                        break;
                    }
                    System.out.print("Nombre del objeto a eliminar: ");
                    String nom = scan.nextLine();
                    src.Objeto obj = m.consultarObjetoDeUsuario(user, nom);
                    if (obj == null) {
                        System.out.println("El usuario no tiene este objeto");
                        break;
                    }
                    m.eliminarObjetoDeUsuario(user, nom);
                    System.out.println("src.Objeto eliminado");
                    scan.nextLine();
                    break;
                }
                case 8: {
                    break;
                }
            }
        }
    }

    }
}
