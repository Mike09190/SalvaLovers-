import java.util.*;

public class Carpeta implements Elemento{
    String nombre;
    List<Elemento> elementos = new ArrayList<>()>;
    
    /**
     * Método para agregar un elemento a la carpeta.
     * @param elemento El elemento a agregar.
     */
    public void agregarElemento(Elemento elemento){
        elementos.add(elemento);
    }

    @Override 
    public int getTamanio() {
        int total = 0;
        for (Elemento elemento : elementos) {
            total += elemento.getTamanio();
        }
        return total;
    }

}