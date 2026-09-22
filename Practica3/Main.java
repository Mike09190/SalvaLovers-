import java.util.ArrayList;
import java.util.List;

// CODIGO INICIAL DE LA PRACTICA 3.
// No esta refactorizado: el objetivo es que el equipo detecte y mejore su diseno.
abstract class Archivo {
    String nombre;
    int tamanio;

    Archivo(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
    }
}

class ArchivoPDF extends Archivo {
    ArchivoPDF(String nombre, int tamanio) {
        super(nombre, tamanio);
    }
}

class ArchivoTexto extends Archivo {
    ArchivoTexto(String nombre, int tamanio) {
        super(nombre, tamanio);
    }
}

class Carpeta {
    String nombre;
    List<Archivo> archivos = new ArrayList<>();
    List<Carpeta> subcarpetas = new ArrayList<>();

    Carpeta(String nombre) {
        this.nombre = nombre;
    }
}

class CorreoLegacy {
    void send_email(String to, String body) {
        System.out.println("Para: " + to);
        System.out.println(body);
    }
}

public class Main {
    static void agregarArchivo(Carpeta carpeta,
            String tipo, String nombre, int tamanio) {
        if (tipo.equals("pdf")) {
            carpeta.archivos.add(new ArchivoPDF(nombre, tamanio));
        } else if (tipo.equals("txt")) {
            carpeta.archivos.add(new ArchivoTexto(nombre, tamanio));
        }
    }

    static int obtenerTamanio(Carpeta carpeta) {
        int total = 0;
        for (Archivo archivo : carpeta.archivos) {
            total += archivo.tamanio;
        }
        for (Carpeta subcarpeta : carpeta.subcarpetas) {
            total += obtenerTamanio(subcarpeta);
        }
        return total;
    }

    static void enviarResultado(Carpeta carpeta, String destino) {
        CorreoLegacy correo = new CorreoLegacy();
        correo.send_email(destino,
                "Tamanio total: " + obtenerTamanio(carpeta));
    }

    //MÉTODO DE AYUDA
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
        Carpeta clase = new Carpeta("MyP");
        agregarArchivo(clase, "pdf", "practica.pdf", 120);
        agregarArchivo(clase, "txt", "notas.txt", 80);

        Carpeta ejemplos = new Carpeta("Ejemplos");
        agregarArchivo(ejemplos, "txt", "ejemplo.txt", 50);
        clase.subcarpetas.add(ejemplos);

        System.out.println(obtenerTamanio(clase));
        enviarResultado(clase, "profesor@universidad.edu");



        

        //ARCHIVOS DE PRUEBA ANTERIORES
           System.out.println("\n Prueba 1: \n");
            Carpeta vacia = new Carpeta("Vacia"); // preparar
            int total = obtenerTamanio(vacia); // ejecutar
            comprobar("Carpeta vacia", 0, total); // comprobar
        
        
            System.out.println("\n Prueba 2: \n");
            Carpeta carpeta2 = new Carpeta("PDF 120"); // preparar
            agregarArchivo(carpeta2, "pdf", "pdf2", 120);
            int total2 = obtenerTamanio(carpeta2); // ejecutar
            comprobar("Prueba2", 120, total2); // comprobar
        
        
             System.out.println("\n Prueba 3: \n");
            Carpeta carpeta3 = new Carpeta("PDF 120"); // preparar
            agregarArchivo(carpeta3, "txt", "pdf3", 120);
            agregarArchivo(carpeta3, "txt", "txt3", 80);
            int total3 = obtenerTamanio(carpeta3); // ejecutar
            comprobar("Prueba3", 200, total3); // comprobar
        
        
             System.out.println("\n Prueba 4: \n");
            Carpeta carpeta4 = new Carpeta("PDF 120"); // preparar
            agregarArchivo(carpeta4, "txt", "pdf3", 120);
            agregarArchivo(carpeta4, "txt", "txt3", 80);
            Carpeta subcarpeta = new Carpeta("Subcarpeta");
             agregarArchivo(subcarpeta, "txt", "pdf4", 50);
            carpeta4.subcarpetas.add(subcarpeta);
            int total4 = obtenerTamanio(carpeta4); // ejecutar
            comprobar("Prueba4", 250, total4); // comprobar
        
        
            System.out.println("\n Prueba 5: \n");
            Carpeta carpeta5 = new Carpeta("Carpeta5"); // preparar
             agregarArchivo(carpeta4, "txt", "pdf3", 0);
            int total5 = obtenerTamanio(carpeta5); // ejecutar
            comprobar("Carpeta5", 0, total5); // comprobar
        

    }
}
