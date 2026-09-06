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
     */

    /**
     * Notas de como implementar el método insertar:
     * 1. Calcular el índice usarno hashFunction(key)
     * 2.Recorrer la tabla mediante un bucle while hasta encontrar una casilla disponible
     * 
     */
    public void insertar (int key, V value){
        
    }

    /**
     * Método para buscar un valor en la tabla hash
     * @param key Clave del valor a buscar
     * @return valor encontrado o null si no se encuentra
     */


    /**
     * Método para eliminar un valor de la tabla hash
     * @param key Clave del valor a eliminar
     * @return boolean true si se eliminó correctamente, false si no se encontró
     */

    /**
     * Método para imprimir la tabla hash  
     */
}
