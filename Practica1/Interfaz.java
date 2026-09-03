import java.util.Scanner;

public class Interfaz {
    public static void main(String[] args) {
        //Creación de variables
        int tamanio = 7;
        int key;
        char eleccion;
        boolean continuar = true;
        //Creación de Objetos
        Scanner sc = new Scanner(System.in);
        //Creación de la tabla hash y primer elemento a guardar
        HashTable<String> h = new HashTable<>(tamanio);
        System.out.println("Ingresa la llave del primer elemento: ");
        while (true) {
            try {
                key = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("ingresa una llave valida");
                sc.nextLine();
            }
        }
        sc.nextLine();  
        System.out.println("Ingresa el elemento a guardar: ");
        String valorAGuardar = sc.nextLine();
        h.insertar(key, valorAGuardar); //Insertamos primer elemento

        do {
            System.out.println("Que otra acción deseas realizar:");
            System.out.println("1) Ingresar otro elemento ");
            System.out.println("2) Buscar un elemento en la tabla");
            System.out.println("3) Eliminar un elemento de la tabla");
            System.out.println("4) Imprimir tabla");
            System.out.println("5) Salir");
            eleccion = sc.next().charAt(0);

                switch (eleccion) {
                    case '1': // Insertar elementos
                    System.out.println("Ingresa la llave del elemento a guardar: ");
                    while (true) {
                        try {
                            key = sc.nextInt();
                            sc.nextLine(); // Limpia el buffer
                            break;
                        } catch (Exception e) {
                            System.out.println("ingresa una llave valida");
                            sc.nextLine();
                        }
                    }
                    System.out.println("Ingresa el elemento a guardar: ");
                    valorAGuardar = sc.nextLine();
                    h.insertar(key, valorAGuardar);
                    break;
                    case '2': //Buscar elementos
                        int llave  = 0;
                        System.out.println("Ingresa la llave del elemento a buscar:");
                        while (true) {
                            try {
                                llave = sc.nextInt();
                                sc.nextLine(); // Limpia el buffer
                                break;
                            } catch (Exception e) {
                            System.out.println("ingresa una llave valida");
                            sc.nextLine();
                            }
                        }
                        String busqueda = h.busqueda(llave);
                        if (busqueda != null) {
                            System.out.println("El elemento con la llave " + llave + " es: " + busqueda);
                        } else {
                            System.out.println("El elemento con la llave " + llave + " no se encuentra en la tabla");
                        }
                        break;
                    case '3': //Eliminar elementos
                        System.out.println("Ingresa la llave del elemento a eliminar:");
                        while (true) {
                            try {
                                key = sc.nextInt();
                                sc.nextLine(); // Limpia el buffer
                                break;
                            } catch (Exception e) {
                            System.out.println("ingresa una llave valida");
                            sc.nextLine();
                            }
                        }

                        if (h.eliminar(key)) {
                            System.out.println("El elemento ha sido eliminado de la tabla ");
                        } else {
                            System.out.println("El elemento a eliminar NO se encuentra en la tabla ");
                        }
                        break;
                    case '4': //Imprimir tabla
                        h.imprimirTabla();
                        break;
                    case '5': //Salir del programa
                        System.out.println("Adios");
                        continuar = false;
                        break;
                    default: //Opcion no valida
                        System.out.println("Opcion no valida, intente de nuevo");
                        break;
                }


        } while (continuar);

        sc.close();
    }
}
