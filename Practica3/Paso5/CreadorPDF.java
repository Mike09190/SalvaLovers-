public class CreadorPDF extends CreadorArchivo{

    @Override
    public Archivo crearArchivo(String nombre, int tamanio){
        return new ArchivoPDF(nombre, tamanio);
    }
} 