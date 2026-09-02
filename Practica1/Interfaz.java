import java.util.Scanner;

public class Interfaz {
    public static void main(String[] args) {
        int tamanio = 7;
        int key;
        Scanner sc = new Scanner(System.in);
        HashTable h = new HashTable(tamanio);
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
        System.out.println("Ingresa el elemento a guardar: ");
        String valorAGuardar = sc.next();
        Nodo<String> n = new Nodo<>(key, valorAGuardar);
        h.insertar(n);
        int eleccion = 0;
        do {
            System.out.println("Que otra acción deseas realizar:");
            System.out.println("1) Ingresar otro elemento ");
            System.out.println("2) Buscar un elemento en la tabla");
            System.out.println("3) Eliminar un elemento de la tabla");
            System.out.println("4) Imprimir tabla");
            System.out.println("5) Salir");
            try {
                eleccion = sc.nextInt();

                switch (eleccion) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Ingresa la llave del elemento");
                        int key2 = sc.nextInt();
                        System.out.println("Ingresa el elemento a guardar: ");
                        String valor = sc.next();
                        Nodo<String> x = new Nodo<>(key2, valor);
                        h.insertar(x);
                        break;
                    case 2:
                        System.out.println("Ingresa la llave del elemento a buscar:");
                        int llave = sc.nextInt();
                        if (h.busqueda(llave)) {
                            System.out.println("El elemento se encuentra en la tabla ");
                        } else {
                            System.out.println("El elemento NO se encuentra en la tabla ");
                        }
                        break;
                    case 3:
                        System.out.println("Ingresa la llave del elemento a eliminar:");
                        int cont = sc.nextInt();
                        if (h.eliminar(cont)) {
                            System.out.println("El elemento ha sido eliminado de la tabla ");
                        } else {
                            System.out.println("El elemento a eliminar NO se encuentra en la tabla ");
                        }
                        break;
                    case 4:
                        h.imprimirTabla();
                        break;
                    case 5:
                        System.out.println("Adios");
                        break;
                    default:
                        System.out.println("Opcion no valida, intente de nuevo");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Debes introducir un número");
            }

        } while (eleccion != 5);

        sc.close();
    }
}
