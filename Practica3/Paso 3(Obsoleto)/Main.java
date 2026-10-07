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
    static void agregarArchivo(Carpeta carpeta,
            String tipo, String nombre, int tamanio) {
        if (tipo.equals("pdf")) {
            carpeta.agregarElemento(new ArchivoPDF(nombre, tamanio));
        } else if (tipo.equals("txt")) {
            carpeta.agregarElemento(new ArchivoTexto(nombre, tamanio));
        }
    }

    static int obtenerTamanio(Carpeta carpeta) {
        return carpeta.getTamanio();
    }

    static void enviarResultado(Carpeta carpeta, String destino) {
        CorreoLegacy correo = new CorreoLegacy();
        correo.send_email(destino,
                "Tamanio total: " + obtenerTamanio(carpeta));
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
    }
}