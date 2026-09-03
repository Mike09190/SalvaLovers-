/**
 * Clase que representa un nodo de la hashtable.
 * Alamecena un par de datos compuestos por una llave entera y un valor
 * generico.
 * 
 * @author SalvaLovers
 * @version 1.0
 */
public class Nodo<V> {

    // Atributos de la clase
    private int llave;
    private V valor;

    /**
     * Constructor con parametros que inicializa el nodo con una llave y su valor
     * asociado.
     * 
     * @param llave identificador unico entero del nodo.
     * @param valor valor-dato-contenido de tipo generico que almacena en el nodo
     */
    public Nodo(int llave, V valor) {
        this.llave = llave;
        this.valor = valor;
    }

    /**
     * Metodo que regresa la llave asociada al nodo.
     * 
     * @return int la llave del nodo.
     */
    public int obtenerLlave() {
        return this.llave;
    }

    /**
     * Metodo que regresa el valor acosicado en el nodo
     * 
     * @return V el valor asociado en el nodo.
     */
    public V obtenerValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        return "(" + this.llave + ": \"" + this.valor + "\")";
    }
}