public class CreadorTxt extends CreadorArchivo{
    @Override
    public Archivo crearAchivo(String nombre, int tamanio){
        return new ArchivoTexto(nombre, tamanio);
    }
}