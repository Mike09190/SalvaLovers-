import java.util.*;

public class HashTableDireccionamiento<V>{
//Creacion de atributos
private ArrayList<NodoDireccionamientoAbierto<V>> htabla;
private int tamano;


    /**
    * Método constructor de la clase HashTableDireccionamiento
    * @param tamano tamaño de la tabla hash
    */
    public HashTableDireccionamiento(int tamano){
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
    public int hashFunction(int key){
        return (key % tamano);
    }

    /**
     * Método para buscar un índice disponible
     * @param key clave para buscar un índice disponible
     * @return int índice disponible o -1 en caso de que no haya índices disponibles
     */
    private int indiceDisponible(int key){
        int indice = hashFunction(key);
        int indiceActual;
        //Usamos la bandera para guardar la primera posicion eliminada con la bandera "Deleted" en caso de que al final no se encuentre un índice null o igual
        // devolvemos la primera posición con valor "Deleted" que se encontró o -1 en caso de que no se haya encontrado ninguna posición disponible
        int bandera = -1;

        for(int i=0; i< tamano; i++){
           indiceActual = (indice + i) % tamano;
           NodoDireccionamientoAbierto<V> nodo = htabla.get(indiceActual);
           if(nodo == null ){
            if(bandera != -1){
                return bandera;
            }
            return indiceActual;
           }
           if(!nodo.estaEliminado() && nodo.obtenerLlave() == key){
            return indiceActual;
           }
           if(nodo.estaEliminado() && bandera == -1){
            bandera = indiceActual;
           }
        }

        return bandera;
    }

    /**
     * Método para insertar valores en la tabla hash
     * @param key clave del valor a insertar
     * @param value valor a insertar
     * @throws IllegalStateException si la tabla esta llena y no hay ninguna casilla
     * null, elimanada o con la misma llave disponible 
     */
    public void insertar (int key, V value){
        int indice = indiceDisponible(key);

        // Si no se encontro ninguna posicion disponible, la tabla esta llena
        if(indice == -1){
        throw new IllegalStateException("La tabla hash esta llena, no se puede insertar la llave " + key);
        }
        // Se crea un nodo nuevo y se coloca en la posicion encontrada, ya sea que 
        // estuviera vacia, eliminada, o era la misma llave 
        htabla.set(indice, new NodoDireccionamientoAbierto<>(key,value));
    }

    /**
     * Método para buscar un valor en la tabla hash
     * @param key Clave del valor a buscar
     * @return valor encontrado o null si no se encuentra
     */
    public V buscar(int key){
        int indice = hashFunction(key);
        
        for(int i = 0; i < tamano ; i++){
            int indiceActual = (indice + i) % tamano;
            NodoDireccionamientoAbierto<V> nodo = htabla.get(indiceActual);

            // Si llegamos a una casilla nunca usada, la llave no esta en la tabla
            if(nodo == null){
                return null;
            }
            // Solo se compara si el nodo sigue activo (no eliminado)
            if(!nodo.estaEliminado() && nodo.obtenerLlave() == key){
                return nodo.obtenerValor();
            }
            // Si el nodo esta eliminado o es otra llave, seguimos sondeando
        }
        return null;
    }


    /**
     * Método para eliminar un valor de la tabla hash
     * @param key Clave del valor a eliminar
     * @return boolean true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminar(int key){
        int indice = indiceDisponible(key);

        
    }

    /**
     * Método para imprimir la tabla hash  
     */
}
