import java.util.LinkedList;
import java.util.ArrayList;
/**
 * Clase de HasTable utilizando LinkedList para un encadenamiento directo
 * HashTable
 */
public class HashTable<V> {
    //Atributos de la clase
    private int cantidadElementos;
    private int tamano = 7;
    //Uso de la clase ArrayList para poder utilizar valores génericos sin arrojar ninguna advertencia
    private ArrayList<LinkedList<Nodo<V>>> htabla; // 
    /**
     * Método constructor con parámetros
     * Declara los elementos de la tabla hash como LinkedList
     * @param tamano
     */
    public HashTable(int tamano) {
        this.tamano = tamano;
        this.cantidadElementos = 0;

        this.htabla = new ArrayList<>(tamano);

        for (int i = 0; i < tamano; i++) {
            this.htabla.add(new LinkedList<>());
        }
    }
    /**
     * Método privado que realiza la función de dispersión con módulo 7
     * @param k
     * @return int indice
     */
    private int hashFuncion(int k) {
        return (k % tamano);
    }

    /**
     * Método de insertar que solicita la llave y el valor a ingresar un elemento a la table
     * En caso de existir un elemento con la misma llave el programa reemplaza el valor por el nuevo
     * @param key
     * @param value
     */
    public void insertar(int key, V value){
        Nodo<V> nodo = new Nodo<V>(key, value);
        //Buscamos si ya existe la llave
        if (existeElemento(key)){
            eliminar(key);
        }
        int indice = hashFuncion(key);

        htabla.get(indice).add(nodo);
        cantidadElementos ++;
    }

    /**
     * Método booleano que regresa true en caso de encontrar el elemento, caso contrario False
     * @param key
     * @return boolean
     */
    public boolean existeElemento(int key) {
        int indice = hashFuncion(key);
        LinkedList<Nodo<V>> listaBuscar = htabla.get(indice);

        for (Nodo<V> v : listaBuscar) {
            if (v.obtenerLlave() == key) {
                return true;
            }
        }
        return false;
    }
    /**
     * Método dn existeElemento que al pasar una llave regresa el elemento asociado
     * En caso de regresar "Null" no se encontro el elemento
     * @param key
     * @return
     */
    public V busqueda(int key){
        int indice = hashFuncion(key);
        LinkedList<Nodo<V>> listaBuscar = htabla.get(indice);

        for (Nodo<V> v : listaBuscar) {
            if (v.obtenerLlave() == key) {
                return v.obtenerValor();
            }
        }
        return null;
    }
    /**
     * Método de eliminar. Al pasar una llave el programa borra el elemento asociado
     * @param key
     * @return true en caso de encontrarlo y eliminarlo caso contrario False
     */
    public boolean eliminar(int key) {
        int indice = hashFuncion(key);
        if(existeElemento(key)) {
            LinkedList<Nodo<V>> listaEliminar = htabla.get(indice);
            for (Nodo<V> v : listaEliminar) {
                if (v.obtenerLlave() == key) {
                    listaEliminar.remove(v);
                    cantidadElementos--;
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Método que calcula el factor de carga de la tabla hash
     * @return double factor de carga
     */
    public double factorCarga() {
        return (double) cantidadElementos / tamano;
    }
    /**
    * Método que imprime la tabla hash
    */
    public void imprimirTabla() {
        System.out.println(" ---- TABLA HASH PRO -----");
        for (int i = 0; i < tamano; i++) {
            System.out.print(i + " -> ");
            LinkedList<Nodo<V>> lista = htabla.get(i);
            for (Nodo<V> n : lista) {
                System.out.print(n.toString() + " -> ");
            }
            System.out.println();
        }
    }
}
