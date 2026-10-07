public class CreadorPDF extends CreadorArchivo{
    @Override
    Archivo CreadorArchivo(String nombre, int tamanio){
        return new crearAchivo(nombre, tamanio);
    }
} 