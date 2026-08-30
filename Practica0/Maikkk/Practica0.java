import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programa para la getion basica de calificaciones de un estudiente
 * 
 * Solicita y valida el nombre del alumno y sus tres calificaciones,
 * calcula el promedio final y determina su estatus academico
 * (APROBADO o REPROBADO) segun la escala academica.
 * 
 * @author SalvaLovers
 * @version 1.1
 */
public class Practica0 {

    public static void main(String[] args) {
        // Creacion de objetos
        Scanner scan = new Scanner(System.in);
        // Creacion de Variables
        String nombre;
        boolean nombreCorrecto = true;
        double[] calificaciones = new double[3];
        double promedio = 0;

        // Verificar que el nombre no contenga otros caracteres que no sean letras
        do {
            System.out.print("Ingrese el nombre del alumno: ");
            nombre = scan.nextLine();
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                System.out.println("El nombre no puede contener caracteres de otro tipo");
                nombreCorrecto = false;
            } else {
                nombreCorrecto = true;
            }
        } while (!nombreCorrecto);

        System.out.println("Ingrese las calificaciones del alumno (0 - 10):");
        /**
         * Iteracion para guardar las calificaciones del estudiante
         * Se guarda en un arreglo en caso de querer utilizar sus calificaciones para
         * una operación diferente
         * Tambien va calculando el promedio del estudiante
         */
        for (int i = 0; i < calificaciones.length; i++) {
            boolean valida = false;
            while (!valida) {
                try {
                    System.out.print("Ingresa la calificacion " + (i + 1) + ": ");
                    calificaciones[i] = scan.nextDouble();

                    if (calificaciones[i] < 0 || calificaciones[i] > 10) {
                        System.out.println("Error: La calificacion debe estar entre 0 y 10.");
                        calificaciones[i] = 0;
                    } else {
                        promedio += calificaciones[i];
                        valida = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingrese un numero valido.");
                    scan.nextLine();
                }
            }
        }
        // Calcular el promedio
        promedio /= calificaciones.length;
        // Mostrar resultado del estudiante
        System.out.println("\n- RESULTADO -");
        System.out.println("Alumno: " + nombre);
        System.out.printf("Promedio final: %.2f\n", promedio);
        // Revisar si es aprobatorio o no
        if (promedio < 6) {
            System.out.println("Estatus: REPROBADO");
        } else {
            System.out.println("Estatus: APROBADO");
        }

        scan.close();
    }
}
