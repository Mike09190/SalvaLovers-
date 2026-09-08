import java.util.*;

public class HashTableDireccionamiento<V> {

    // Creación de atributos
    private ArrayList<NodoDireccionamientoAbierto<V>> htabla;
    private int tamano;

    /**
     * Método constructor de la clase HashTableDireccionamiento
     * @param tamano tamaño de la tabla hash
     */
    public HashTableDireccionamiento(int tamano) {
        this.tamano = tamano;
        htabla = new ArrayList<>(tamano);
        for (int i = 0; i < tamano; i++) {
            htabla.add(null);
        }
    }

    /**
     * Método para calcular el índice de la tabla hash
     * @param key clave para calcular el índice
     * @return indice calculado 
     */
    public int hashFunction(int key) {
        return (key % tamano);
    }

    /**
     * Método para buscar un índice disponible
     * @param key clave para buscar un índice disponible
     * @return int índice disponible o -1 en caso de que no haya índices disponibles
     */
    private int indiceDisponible(int key) {
        int indice = hashFunction(key);
        int indiceActual;
        int bandera = -1;

        for (int i = 0; i < tamano; i++) {
            indiceActual = (indice + i) % tamano;
            NodoDireccionamientoAbierto<V> nodo = htabla.get(indiceActual);
            
            if (nodo == null) {
                if (bandera != -1) {
                    return bandera;
                }
                return indiceActual;
            }
            if (!nodo.estaEliminado() && nodo.obtenerLlave() == key) {
                return indiceActual;
            }
            if (nodo.estaEliminado() && bandera == -1) {
                bandera = indiceActual;
            }
        }

        return bandera;
    }

    /**
     * Método para insertar valores en la tabla hash
     * @param key clave del valor a insertar
     * @param value valor a insertar
     * @throws IllegalStateException si la tabla esta llena
     */
    public void insertar(int key, V value) {
        int indice = indiceDisponible(key);

        if (indice == -1) {
            throw new IllegalStateException("La tabla hash esta llena, no se puede insertar la llave " + key);
        }
        
        htabla.set(indice, new NodoDireccionamientoAbierto<>(key, value));
    }

    /**
     * Método para buscar un valor en la tabla hash
     * @param key Clave del valor a buscar
     * @return valor encontrado o null si no se encuentra
     */
    public V buscar(int key) {
        int indice = hashFunction(key);
        
        for (int i = 0; i < tamano; i++) {
            int indiceActual = (indice + i) % tamano;
            NodoDireccionamientoAbierto<V> nodo = htabla.get(indiceActual);

            if (nodo == null) {
                return null;
            }
            if (!nodo.estaEliminado() && nodo.obtenerLlave() == key) {
                return nodo.obtenerValor();
            }
        }
        return null;
    }

    /**
     * Método para eliminar un valor de la tabla hash
     * @param key Clave del valor a eliminar
     * @return boolean true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminar(int key) {
        int indice = hashFunction(key);

        for (int i = 0; i < tamano; i++) {
            int indiceActual = (indice + i) % tamano;
            NodoDireccionamientoAbierto<V> nodo = htabla.get(indiceActual);

            if (nodo == null) {
                return false;
            }

            if (!nodo.estaEliminado() && nodo.obtenerLlave() == key) {
                nodo.eliminado = true;
                return true;
            }
        }

        return false;
    }

    public void imprimirTabla () {
        System.out.println(" ---- TABLA HASH PRO -----");
        for (int i = 0; i < tamano; i++) {
            NodoDireccionamientoAbierto<V> nodo = htabla.get(i);
            System.out.print("indice " + i + ": ");
            
            if (nodo == null) {
                System.out.println("Vacio");
            } else if (nodo.estaEliminado()) {
                System.out.println("Eliminado");
            } else {
                System.out.println("Llave: " + nodo.obtenerLlave() + ", Valor: " + nodo.obtenerValor());
            }
        }
    }
}
