import java.util.Scanner;
import java.util.InputMismatchException;

public class Calificaciones {
    public static void main(String[] args) {

        double[] calificacion = new double[5];
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa tu nombre");
        String nombre = sc.nextLine();

        System.out.println("Ingresa tres calificaciones");

        for (int i = 0; i < 3; i++) {

            while (true) {
                try {
                    System.out.print("Ingresa la calificación " + (i + 1) + ": ");
                    calificacion[i] = sc.nextDouble();

                    if (calificacion[i] < 0 || calificacion[i] > 10) {
                        System.out.println("La calificación debe estar entre 0 y 10.");
                        continue;
                    }

                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida, ingresa un número válido.");
                    sc.nextLine();
                }
            }
        }

        double sumaArreglo = 0.0;

        for (int j = 0; j < calificacion.length; j++) {
            sumaArreglo += calificacion[j];
        }

        double promedio = sumaArreglo / calificacion.length;

        System.out.println(nombre + " " + promedio);

        if (promedio >= 6.0) {
            System.out.println("Aprobado");
        } else {
            System.out.println("Reprobado");
        }

        sc.close();
    }
}
