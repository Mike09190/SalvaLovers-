import java.util.ArrayList;
import java.util.List;

// CODIGO INICIAL DE LA PRACTICA 3.
// No esta refactorizado: el objetivo es que el equipo detecte y mejore su diseno.

/* 
class Carpeta  implements Elemento {
    String nombre;
    List<Archivo> archivos = new ArrayList<>();
    List<Carpeta> subcarpetas = new ArrayList<>();

    Carpeta(String nombre) {
        this.nombre = nombre;
    }
}
*/
class CorreoLegacy {
    void send_email(String to, String body) {
        System.out.println("Para: " + to);
        System.out.println(body);
    }
}

public class Main {
    

    static int obtenerTamanio(Carpeta carpeta) {
        return carpeta.getTamanio();
    }

    static void enviarResultado(Carpeta carpeta, String destino) {
        CorreoLegacy correo = new CorreoLegacy();
        correo.send_email(destino,
                "Tamanio total: " + obtenerTamanio(carpeta));
    }

        static void comprobar(String nombre, int esperado, int obtenido) {
        if (esperado == obtenido) {
        System.out.println("OK: " + nombre);
        } else {
        System.out.println("FALLO: " + nombre
        + " | esperado=" + esperado
        + " | obtenido=" + obtenido);
        }
        }

    public static void main(String[] args) {

        //Prueba del ejemplo con el paso 4

        CreadorArchivo creadorPDF = new CreadorPDF();
        CreadorArchivo creadorTexto = new CreadorTexto();

        Carpeta clase = new Carpeta("Clase");

        Archivo practica = creadorPDF.crearArchivo("practica.pdf", 120);
        Archivo texto = creadorTexto.crearArchivo("notas.txt", 80);
        clase.agregarElemento(practica);
        clase.agregarElemento(texto);


        Carpeta ejemplos = new Carpeta("Ejemplos");
        Archivo ejemplo = creadorTexto.crearArchivo("ejemplo.txt", 50);
        ejemplos.agregarElemento(ejemplo);
        clase.agregarElemento(ejemplos);

        System.out.println(clase.getTamanio());
        enviarResultado(clase, "profesor@universidad.edu");
        int total = clase.getTamanio();
        comprobar("Prueba archivos del ejemplo", 250, total);

        
            //CASOS DE PRUEBA PASO 4 


           System.out.println("\n Prueba 1: \n");
            Carpeta vacia = new Carpeta("Vacia"); // preparar
            int total1 = vacia.getTamanio(); // ejecutar
            comprobar("Carpeta vacia", 0, total1); // comprobar
        
        
            System.out.println("\n Prueba 2: \n");
            Carpeta carpeta2 = new Carpeta("PDF 120"); // preparar
            Archivo prueba2 = creadorPDF.crearArchivo("pdf2", 120);
            carpeta2.agregarElemento(prueba2);
            int total2 = carpeta2.getTamanio(); // ejecutar
            comprobar("Prueba2", 120, total2); // comprobar
        
        
             System.out.println("\n Prueba 3: \n");
            Carpeta carpeta3 = new Carpeta("PDF 120"); // preparar
            Archivo prueba3_1 = creadorPDF.crearArchivo("pdf3", 120);
            Archivo prueba3_2 = creadorTexto.crearArchivo("text3", 80);
            carpeta3.agregarElemento(prueba3_1);
            carpeta3.agregarElemento(prueba3_2);
            int total3 = carpeta3.getTamanio(); // ejecutar
            comprobar("Prueba3", 200, total3); // comprobar
        
            //Las demás comprobaciones las prueba otro del equipo
             System.out.println("\n Prueba 4: \n");
            Carpeta carpeta4 = new Carpeta("PDF 120"); // preparar
            carpeta4.agregarElemento(prueba3_1);
            carpeta4.agregarElemento(prueba3_2);
            Carpeta subcarpeta = new Carpeta("Subcarpeta");
            Archivo prueba4 = creadorTexto.crearArchivo("text4", 50);
            subcarpeta.agregarElemento(prueba4);
            carpeta4.agregarElemento(subcarpeta);
            int total4 = carpeta4.getTamanio(); // ejecutar
            comprobar("Prueba4", 250, total4); // comprobar
        
        
            System.out.println("\n Prueba 5: \n");
            Carpeta carpeta5 = new Carpeta("Carpeta5"); // preparar
            Archivo nulo = creadorPDF.crearArchivo("Nulo", 0);
            carpeta5.agregarElemento(nulo);
            int total5 = carpeta5.getTamanio(); // ejecutar
            comprobar("Carpeta5", 0, total5); // comprobar
        


    }
}
