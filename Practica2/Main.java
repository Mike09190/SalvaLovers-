import java.util.Scanner;

/**
 * Clase para probar el Arbol B
 * Menu interactivo.
 * Caso Prueba
 * 
 * @author SalvaLovers
 * @version 2.0
 */
public class Main {

	public static void main(String[] args) {

		int key;
		char eleccion;
		boolean continuar = true;

		Scanner sc = new Scanner(System.in);
		ArbolB arbol = new ArbolB();

		do {
			System.out.println("\n¿Qué acción deseas realizar?");
			System.out.println("1) Insertar una llave");
			System.out.println("2) Buscar una llave");
			System.out.println("3) Eliminar una llave");
			System.out.println("4) Imprimir árbol");
			System.out.println("5) Ejecutar prueba obligatoria");
			System.out.println("6) Salir");

			eleccion = sc.next().charAt(0);

			switch (eleccion) {
				case '1': // Insertar
					System.out.println("Ingresa la llave a insertar:");
					while (true) {
						try {
							key = sc.nextInt();
							sc.nextLine();
							break;
						} catch (Exception e) {
							System.out.println("Ingresa una llave válida.");
							sc.nextLine();
						}
					}

					if (arbol.buscar(key)) {
						System.out.println("La llave ya se encuentra en el árbol.");
					} else {
						arbol.insertar(key);
						System.out.println("La llave fue insertada correctamente.");
					}
					break;

				case '2': // Buscar
					System.out.println("Ingresa la llave a buscar:");
					while (true) {
						try {
							key = sc.nextInt();
							sc.nextLine();
							break;
						} catch (Exception e) {
							System.out.println("Ingresa una llave válida.");
							sc.nextLine();
						}
					}

					if (arbol.buscar(key)) {
						System.out.println("La llave " + key + " se encuentra en el árbol.");
					} else {
						System.out.println("La llave " + key + " NO se encuentra en el árbol.");
					}
					break;

				case '3': // Eliminar
					System.out.println("Ingresa la llave a eliminar:");
					while (true) {
						try {
							key = sc.nextInt();
							sc.nextLine();
							break;
						} catch (Exception e) {
							System.out.println("Ingresa una llave válida.");
							sc.nextLine();
						}
					}

					if (arbol.buscar(key)) {
						arbol.eliminar(key);
						System.out.println("La llave fue eliminada correctamente.");
					} else {
						System.out.println("La llave a eliminar NO se encuentra en el árbol.");
					}
					break;

				case '4': // Imprimir
					System.out.println("Árbol B por niveles:");
					arbol.imprimirPorNiveles();
					break;

				case '5': // Prueba obligatoria
					ArbolB prueba = new ArbolB();
					int[] llaves = { 20, 40, 10, 30, 50, 60, 70, 5, 15, 25, 35, 45 };

					System.out.println("Ejecutando prueba obligatoria...");
					for (int llave : llaves) {
						prueba.insertar(llave);
					}

					System.out.println("\nÁrbol después de las inserciones:");
					prueba.imprimirPorNiveles();

					System.out.println("\nResultado esperado:");
					System.out.println("Nivel 0: [45]");
					System.out.println("Nivel 1: [15 | 30] [60]");
					System.out.println("Nivel 2: [5 | 10] [20 | 25] [35 | 40] [50] [70]");

					System.out.println("\nBuscar 35:");
					if (prueba.buscar(35)) {
						System.out.println("FOUND");
					} else {
						System.out.println("NOT_FOUND");
					}

					System.out.println("\nBuscar 99:");
					if (prueba.buscar(99)) {
						System.out.println("FOUND");
					} else {
						System.out.println("NOT_FOUND");
					}

					System.out.println("\nIntentando insertar 35 nuevamente:");
					prueba.insertar(35);
					prueba.imprimirPorNiveles();

					System.out.println("\nEliminando 25:");
					prueba.eliminar(25);
					prueba.imprimirPorNiveles();

					System.out.println("\nEliminando 10:");
					prueba.eliminar(10);
					prueba.imprimirPorNiveles();

					System.out.println("\nEliminando 70:");
					prueba.eliminar(70);
					prueba.imprimirPorNiveles();

					System.out.println("\nEliminando 5:");
					prueba.eliminar(5);
					prueba.imprimirPorNiveles();

					System.out.println("\nResultado final esperado:");
					System.out.println("Nivel 0: [30 | 45]");
					System.out.println("Nivel 1: [15 | 20] [35 | 40] [50 | 60]");

					System.out.println("\nBuscar 25 después de eliminar:");
					if (prueba.buscar(25)) {
						System.out.println("FOUND");
					} else {
						System.out.println("NOT_FOUND");
					}

					System.out.println("\nBuscar 35:");
					if (prueba.buscar(35)) {
						System.out.println("FOUND");
					} else {
						System.out.println("NOT_FOUND");
					}
					break;

				case '6': // Salir
					System.out.println("Adiós.");
					continuar = false;
					break;

				default:
					System.out.println("Opción no válida, intenta de nuevo.");
					break;
			}
		} while (continuar);

		sc.close();
	}
}