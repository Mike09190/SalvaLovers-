import java.util.ArrayList;

/**
 * Clase que representa un nodo de un Árbol B de orden 4.
 * Almacena llaves enteras, referencias a sus hijos, una referencia
 * a su nodo padre y la información necesaria para determinar
 * si el nodo es una hoja.
 *
 * @author SalvaLovers
 * @version 2.0
 */
public class Nodo {
    private ArrayList<Integer> llaves;
    private ArrayList<Nodo> hijos;
    private boolean esHoja;
    private Nodo padre;
    private int numLlaves;
    private int numHijos;

    /**
     * Metodo Constructor.
     * 
     * Construye un nodo vacío.
     * Inicializa sus listas de llaves e hijos, lo marca como hoja
     * y establece sus contadores en cero.
     */
    public Nodo() {
        this.llaves = new ArrayList<>(3);
        this.hijos = new ArrayList<>(4);
        this.esHoja = true;
        this.padre = null;
        this.numLlaves = 0;
        this.numHijos = 0;

    }

    /**
     * Métodos de acceso
     */

    /**
     * Indica si el nodo es una hoja.
     *
     * @return true si el nodo no tiene hijos, false en otro caso
     */
    public boolean esHoja() {
        return this.esHoja;
    }

    /**
     * Regresa el nodo padre.
     *
     * @return padre del nodo o null si no tiene padre
     */
    public Nodo getPadre() {
        return this.padre;
    }

    /**
     * Regresa la cantidad de hijos del nodo.
     *
     * @return número de hijos
     */
    public int getNumHijos() {
        return this.numHijos;
    }

    /**
     * Regresa la cantidad de llaves almacenadas en el nodo.
     *
     * @return número de llaves
     */
    public int getNumLlaves() {
        return this.numLlaves;
    }

    /**
     * Regresa la lista de hijos del nodo.
     *
     * @return lista de nodos hijos
     */
    public ArrayList<Nodo> getHijos() {
        return this.hijos;
    }

    /**
     * Regresa el hijo almacenado en una posición específica.
     *
     * @param indice posición del hijo
     * @return nodo hijo almacenado en el índice indicado
     */

    public Nodo obtenerHijoIndice(int indice) {
        return this.hijos.get(indice);
    }

    /**
     * Obtiene la posición de una llave dentro del nodo.
     *
     * @param llave llave que se desea localizar
     * @return índice de la llave o -1 si no se encuentra
     */
    public int getIndiceLlave(int llave) {
        return this.llaves.indexOf(llave);
    }

    /**
     * Obtiene la posición de un hijo dentro del nodo.
     *
     * @param hijo nodo hijo que se desea localizar
     * @return índice del hijo o -1 si no se encuentra
     */
    public int getIndiceHijo(Nodo hijo) {
        return this.hijos.indexOf(hijo);
    }

    /**
     * Metodos modificadores
     */

    /**
     * Agrega una llave al final de la lista de llaves del nodo.
     *
     * @param llave llave que se desea agregar
     */
    public void setLlave(int llave) {
        this.llaves.add(llave);
        this.numLlaves++;
    }

    /**
     * Reemplaza la llave almacenada en un índice específico.
     *
     * @param indice posición de la llave que se desea reemplazar
     * @param llave  nueva llave que será almacenada
     */
    public void setLlaveIndice(int indice, int llave) {
        this.llaves.set(indice, llave);

    }

    /**
     * Inserta una nueva llave en una posición específica.
     *
     * @param indice posición donde se insertará la llave
     * @param llave  llave que se desea insertar
     */
    public void insertarLlaveIndice(int indice, int llave) {
        this.llaves.add(indice, llave);
        this.numLlaves++;
    }

    /**
     * Agrega un hijo al final de la lista de hijos del nodo.
     * El nodo deja de considerarse hoja y el hijo recibe
     * a este nodo como padre.
     *
     * @param hijo nodo que se desea agregar como hijo
     */
    public void setHijo(Nodo hijo) {
        this.hijos.add(hijo);
        this.numHijos++;
        this.esHoja = false;
        hijo.setPadre(this);
    }

    /**
     * Establece el nodo padre.
     *
     * @param padre nuevo padre del nodo
     */
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    /**
     * Ordena de menor a mayor las llaves almacenadas en el nodo.
     */
    public void ordenar() {
        int temporal;
        for (int i = 0; i < numLlaves - 1; i++) {
            for (int j = 0; j < numLlaves - i - 1; j++) {
                if (llaves.get(j) > llaves.get(j + 1)) {
                    temporal = llaves.get(j);
                    llaves.set(j, llaves.get(j + 1));
                    llaves.set(j + 1, temporal);
                }
            }
        }

    }

    /**
     * Verifica si una llave se encuentra almacenada en el nodo.
     *
     * @param llave llave que se desea buscar
     * @return true si la llave existe en el nodo, false en otro caso
     */
    public boolean buscaLlave(int llave) {
        return this.llaves.contains(llave);
    }

    /**
     * Regresa la llave almacenada en un índice específico.
     *
     * @param indice posición de la llave
     * @return llave almacenada en el índice indicado
     */
    public int obtenLlave(int indice) {
        return this.llaves.get(indice);

    }

    /**
     * Indica si el nodo no contiene llaves.
     *
     * @return true si el nodo está vacío, false en otro caso
     */
    public boolean estaVacio() {
        return this.numLlaves == 0;
    }

    /**
     * Elimina del nodo una llave mediante su valor.
     * Si la llave existe, también actualiza el contador de llaves.
     *
     * @param llave llave que se desea eliminar
     */
    public void borraLlave(int llave) {
        if (this.llaves.remove(Integer.valueOf(llave))) {
            this.numLlaves--;
        }

    }

    /**
     * Elimina del nodo una llave mediante su valor.
     * Si la llave existe, también actualiza el contador de llaves.
     *
     * @param llave llave que se desea eliminar
     */
    public void reemplazar(Nodo nodo, Nodo nodo1, Nodo nodo2) {
        int pos = this.hijos.indexOf(nodo);
        Nodo padre = nodo.getPadre();
        this.hijos.remove(nodo);
        numHijos--;

        this.hijos.add(pos, nodo1);
        this.hijos.add(pos + 1, nodo2);

        numHijos += 2;

        nodo1.setPadre(padre);
        nodo2.setPadre(padre);
    }

    /**
     * Elimina una llave utilizando su índice.
     *
     * @param indice posición de la llave que se desea eliminar
     */
    public void eliminarLlaveIndice(int indice) {
        this.llaves.remove(indice);
        this.numLlaves--;
    }

    /**
     * Elimina un hijo mediante su índice.
     * Si después de eliminarlo el nodo queda sin hijos,
     * el nodo pasa a considerarse una hoja.
     *
     * @param indice posición del hijo que se desea eliminar
     */
    public void eliminarHijoIndice(int indice) {
        this.hijos.remove(indice);
        this.numHijos--;
        if (this.hijos.isEmpty()) {
            this.esHoja = true;
        }
    }

    /**
     * Inserta un hijo en una posición específica.
     * El nodo deja de considerarse hoja y el hijo recibe
     * a este nodo como padre.
     *
     * @param indice posición donde se insertará el hijo
     * @param hijo   nodo que se desea insertar
     */
    public void insertarHijoIndice(int indice, Nodo hijo) {
        this.hijos.add(indice, hijo);
        this.numHijos++;
        this.esHoja = false;
        hijo.setPadre(this);
    }

    /**
     * Inserta un hijo al inicio de la lista de hijos.
     * Se utiliza durante la redistribución desde un hermano izquierdo.
     * El nodo deja de considerarse hoja y se actualiza la referencia
     * al padre del hijo.
     *
     * @param hijo nodo que se desea insertar al inicio
     */
    public void agregarHijoAlInicio(Nodo hijo) {
        this.hijos.add(0, hijo);
        this.numHijos++;
        this.esHoja = false;
        hijo.setPadre(this);
    }

    /**
     * Imprime las llaves almacenadas en el nodo.
     */
    public void imprimirNodo() {
        System.out.print("[");
        for (int i = 0; i < this.numLlaves; i++) {
            System.out.print(this.llaves.get(i));
            if (i < this.numLlaves - 1) {
                System.out.print(" | ");
            }
        }
        System.out.print("]");
    }
}
