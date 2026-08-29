import java.util.InputMismatchException;
import java.util.Scanner;

public class Version2 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String nombre;
        int totalMaterias = 3;
        double suma = 0.0;

        System.out.print("Ingrese el nombre del alumno: ");
        nombre = scan.nextLine();

        System.out.println("Ingrese las calificaciones del alumno (0 - 10):");

        for (int i = 1; i <= totalMaterias; i++) {
            boolean valida = false;
            while (!valida) {
                try {
                    System.out.print("Ingresa la calificacion " + i + ": ");
                    double calificacion = scan.nextDouble();

                    if (calificacion < 0 || calificacion > 10) {
                        System.out.println("Error: La calificacion debe estar entre 0 y 10.");
                    } else {
                        suma += calificacion;
                        valida = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingrese un numero valido.");
                    scan.nextLine();
                }
            }
        }

        double promedio = suma / totalMaterias;

        System.out.println("\n- RESULTADO -");
        System.out.println("Alumno: " + nombre);
        System.out.printf("Promedio final: %.2f\n", promedio);

        if (promedio < 6) {
            System.out.println("Estatus: REPROBADO");
        } else {
            System.out.println("Estatus: APROBADO");
        }

        scan.close();
    }
}