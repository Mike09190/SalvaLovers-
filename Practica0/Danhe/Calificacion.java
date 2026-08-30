import java.util.Scanner;
import java.util.InputMismatchException;

public class Calificacion{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);        

        System.out.println(" ¿APROBADO O REPROBADO?");
        System.out.print(" Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();

        double promedio = 0;
        double calif = 0;
        boolean correcto = false;
        do{
             
        for(int i = 0; i < 3; i++){
            promedio = 0;
             boolean valido = false;

            while(!valido){
              try{
                sc.nextLine();
                
            System.out.print(" Ingrese calificación " + (i+1) + ":");

            calif = sc.nextDouble();

                valido = true;
           
            } catch(InputMismatchException e){
                System.out.println(" Se deben de colocar números entre 1 y 10, inténtelo de nuevo.");
                sc.nextLine();

            }
            }
              
           

            if(calif < 0 || calif > 10){
                System.out.println(" Error, la calificación debe estar entre 0 y 10. Inténtelo de nuevo colocando todas las calificiaciones otra vez.");
                correcto = true;
                calif = 0;
                promedio = 0;
            } else{
            promedio += calif;
            }
             
         }
        }while(correcto);

        promedio = promedio/3;

        if(promedio >= 6){
            System.out.println(" Nombre: " + nombre);
            System.out.println(" Promedio: " + promedio);
            System.out.println(" Estado: APROBADO");
        } else {
            System.out.println(" Nombre: " + nombre);
            System.out.println(" Promedio: " + promedio);
            System.out.println(" Estado: REPROBADO");
        }

    }
}