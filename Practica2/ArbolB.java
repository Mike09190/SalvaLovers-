import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase que representa un Árbol B de orden fijo m = 4.
 * Almacena llaves enteras y mantiene sus propiedades mediante
 * operaciones de búsqueda, inserción, división, eliminación,
 * redistribución y fusión de nodos.
 *
 * @author SalvaLovers
 * @version 2.0
 */
public class ArbolB {

    // Atributos
    private Nodo raiz;
    private int m;
    private int r;
    private int q;
    private int numNiveles;

    /**
     * Método constructor
     */
    public ArbolB() {
        this.raiz = null;
        this.m = 4;
        this.r = m - 1;
        this.q = (m / 2) - 1;
        this.numNiveles = 0;
    }

    // -- Getters --

    /**
     * Regresa la raíz actual del árbol.
     *
     * @return Nodo raíz del árbol o null si el árbol está vacío
     */
    public Nodo getRaiz() {
        return this.raiz;
    }

    /**
     * Regresa el orden del Árbol B.
     *
     * @return int orden m del árbol
     */
    public int getM() {
        return this.m;
    }

    /**
     * Regresa el numero maximo de llaves permitido por nodo
     * 
     * @return int maximo de llaves por nodo
     */
    public int getR() {
        return this.r;
    }

    /**
     * Regresa el numero minimo de llaves permitido en un nodo
     * distinto de la raíz
     * 
     * @return int minimo de llaves por nodo
     */
    public int getQ() {
        return this.q;
    }

    /**
     * Regresa la cantidad de niveles actuales del arbol
     * 
     * @return int numero de niveles del Arbol B
     */
    public int getNumNiveles() {
        return this.numNiveles;
    }

    /**
     * Inserta una llave entera en el Arbol B
     * Si el arbol esta vacio crea la raíz.
     * Si la llave ya existe, el arbol no se modifica.
     * Despues de insertar, realiza un split cuando el nodo
     * supere el maximo de llaves permitido.
     * 
     * @param llave llave que se desea insertar
     */

    public void insertar(int llave) {

        // Si no hay raíz
        if (this.raiz == null) {
            Nodo nuevo = new Nodo();
            nuevo.setLlave(llave);
            this.raiz = nuevo;
            this.numNiveles = 1;
            return;
        }

        Nodo nodo = buscarNodo(llave);
        if (nodo.buscaLlave(llave)) {
            return;
        }
        nodo.setLlave(llave);
        nodo.ordenar();
        if (nodo.getNumLlaves() > r) {
            split(nodo);
        }
    }

    /**
     * Método auxiliar para hacer Split sobre el árbol B
     * Divide el nodo que ha superado el maximo de llaves permitido.
     * La tercera llave se promueve al padre, las llaves restantes,
     * se distribuyen entre dos nuevos nodos y, si es necesario, el
     * split se propaga hacia arriba.
     * 
     * Si el nodo dividio es la raíz, se crea una nueva raíz.
     * 
     * @param Nodo nodo desbordado que sera dividido
     */
    private void split(Nodo nodo) {

        int k3 = nodo.obtenLlave(2);

        Nodo padre = nodo.getPadre();
        Nodo nodo1 = new Nodo();
        Nodo nodo2 = new Nodo();

        nodo1.setLlave(nodo.obtenLlave(0));
        nodo1.setLlave(nodo.obtenLlave(1));

        nodo2.setLlave(nodo.obtenLlave(3));
        // Cuando el nodo del split no es hoja, osea tiene hijos
        if (!nodo.esHoja()) {

            // El nodo izquierdo recibe P0, P1 y P2
            for (int i = 0; i < 3; i++) {
                Nodo hijo = nodo.obtenerHijoIndice(i);

                if (hijo != null) {
                    nodo1.setHijo(hijo);
                }
            }

            // El nodo derecho recibe P3 y P4
            for (int j = 3; j < 5; j++) {
                Nodo hijo = nodo.obtenerHijoIndice(j);

                if (hijo != null) {
                    nodo2.setHijo(hijo);
                }
            }
        }

        // Cuando tiene padre
        if (nodo.getPadre() != null) {
            padre.setLlave(k3);
            padre.ordenar();
            padre.reemplazar(nodo, nodo1, nodo2);
            if (padre.getNumLlaves() > r) {
                split(padre); // Hacemos recursión en caso de que el padre requiera un split
            }
        }

        // Cuando no tiene padre
        else {
            Nodo nuevaRaiz = new Nodo();

            nuevaRaiz.setLlave(k3);
            nuevaRaiz.setHijo(nodo1);
            nuevaRaiz.setHijo(nodo2);

            nodo1.setPadre(nuevaRaiz);
            nodo2.setPadre(nuevaRaiz);

            raiz = nuevaRaiz;
            this.numNiveles++;
        }

    }

    /**
     * Método que busca una llave dentro del Árbol B,
     * La busqueda comienza en la raíz y continua únicamente por
     * el hijo correspondiente al intervalo donde puede encontrarse
     * la llave.
     * 
     * @param llave llave que se desea buscar
     * @return true si la llave existe en el arbol, false en otro caso
     */
    public boolean buscar(int llave) {
        Nodo nodoActual = this.raiz;

        // Caso base árbol vacío
        if (nodoActual == null) {
            return false;
        }

        // Usamos el método auxiliar para buscar el nodo donde se encuentra la llave
        Nodo nodo = buscarNodo(llave);
        if (nodo != null && nodo.buscaLlave(llave)) {
            return true;
        }
        return false;
    }

    /**
     * Método que busca el Nodo
     * Inicia desde la raíz la busqueda del nodo que contiene una llave
     * o de la hoja donde dicha llave debería encontrarse.
     * 
     * @param key llave que se desea localizar
     * @return Nodo nodo que contiene la llave o la hoja correspondiente
     */
    private Nodo buscarNodo(int key) {
        return buscarNodoRecursivo(this.raiz, key);
    }

    /**
     * Método auxiliar
     * Busca recursivamente el nodo correspondiente a una llave.
     * En cada nodo determina el intervalo adecuado y continua
     * únicamente por el hijo acosiado a ese intervalo.
     * 
     * @param nodoActual nodo desde el cual comienza la busqueda
     * @param key        llave que se desea localizar
     * @return nodo que contiene la llave o la hoja donde deberia encontrarse
     */
    private Nodo buscarNodoRecursivo(Nodo nodoActual, int key) {
        // Caso base 1. llegamos a una hoja
        if (nodoActual.esHoja()) {
            return nodoActual;
        }
        // Caso base 2, el nodo ya posee al elemento que se quiere insertar
        if (nodoActual.buscaLlave(key)) {
            return nodoActual;
        }

        // Determinamos el intervalo y continuamos por el hijo correspondiente
        int indice = 0;
        while (indice < nodoActual.getNumLlaves() && key > nodoActual.obtenLlave(indice)) {
            indice++;
        }
        Nodo hijo = nodoActual.obtenerHijoIndice(indice);
        return buscarNodoRecursivo(hijo, key);
    }

    /**
     * Elimina una llave del Arbol B
     * Si la llave no existe el arbol no se modifica
     * Cuando la llave existe, incia el proceso de eliminacion y
     * si es necesario, repara posibles subocupaciones-
     * 
     * @param llave llave que se desea eliminar
     */
    public void eliminar(int llave) {

        // Verificar si existe un árbol
        if (this.raiz == null) {
            return;
        }
        Nodo nodo = buscarNodo(llave); // Devuelve el nodo donde se puede encontrar la llave

        // Si la llave no existe, no modificamos nada.
        if (nodo == null || !nodo.buscaLlave(llave)) {
            return;
        }

        // Llamamos al método recursivo para eliminar una llave
        eliminarRecursivo(nodo, llave);
    }

    /**
     * Elimina recursivamente una llave.
     * Si se encuentra en una hoja, la elimina directamente y comprueba
     * si aparece subocupación. Si se encuentra en un nodo interno,
     * delega el proceso a eliminarInterno
     * 
     * @param nodo  nodo donde se encuentra la llave
     * @param llave llave que se desea eliminar
     */
    private void eliminarRecursivo(Nodo nodo, int llave) {

        // Caso 1: La llave esta en una hoja
        if (nodo.esHoja()) {
            nodo.borraLlave(llave);

            // Si la raiz tiene tratamiento especial
            if (nodo == raiz) {
                repararRaiz();
                return;
            }

            // Si quedo por debajo del minimo
            if (nodo.getNumLlaves() < q) {
                repararUnderflow(nodo);
            }
            return;
        }

        // Caso 2: la llave esta en un nodo interno.
        int indice = nodo.getIndiceLlave(llave);
        eliminarInterno(nodo, indice);

    }

    /**
     * Obtiene el predecesor de una llave almacenada en un nodo interno.
     * Para encontrarlo, desciende por el subárbol izquierdo y continúa
     * por los hijos más a la derecha hasta llegar a una hoja.
     *
     * @param nodo   nodo que contiene la llave
     * @param indice índice de la llave dentro del nodo
     * @return llave predecesora
     */
    private int obtenerPredecesor(Nodo nodo, int indice) {

        Nodo hijo = nodo.obtenerHijoIndice(indice);
        while (!hijo.esHoja()) {
            hijo = hijo.obtenerHijoIndice(hijo.getNumHijos() - 1);
        }
        return hijo.obtenLlave(hijo.getNumLlaves() - 1);
    }

    /**
     * Obtiene el sucesor de una llave almacenada en un nodo interno.
     * Para encontrarlo, desciende por el subárbol derecho y continúa
     * por los hijos más a la izquierda hasta llegar a una hoja.
     *
     * @param nodo   nodo que contiene la llave
     * @param indice índice de la llave dentro del nodo
     * @return llave sucesora
     */
    private int obtenerSucesor(Nodo nodo, int indice) {
        Nodo hijo = nodo.obtenerHijoIndice(indice + 1);

        while (!hijo.esHoja()) {
            hijo = hijo.obtenerHijoIndice(0);
        }
        return hijo.obtenLlave(0);
    }

    /**
     * Elimina una llave almacenada en un nodo interno.
     * Primero intenta sustituirla por su predecesor. Si el hijo izquierdo
     * no dispone de una llave adicional, intenta utilizar el sucesor.
     * Si ninguno puede utilizarse, fusiona los dos hijos junto con
     * la llave que se desea eliminar.
     *
     * @param nodo   nodo interno que contiene la llave
     * @param indice índice de la llave dentro del nodo
     */
    private void eliminarInterno(Nodo nodo, int indice) {
        int llave = nodo.obtenLlave(indice);

        Nodo hijoIzquierdo = nodo.obtenerHijoIndice(indice);
        Nodo hijoDerecho = nodo.obtenerHijoIndice(indice + 1);

        // Caso 1: intentamos primero con el predecesor
        if (hijoIzquierdo.getNumLlaves() > q) {
            int predecesor = obtenerPredecesor(nodo, indice);

            // Sustituimos la llave interna
            nodo.setLlaveIndice(indice, predecesor);

            Nodo nodoPredecesor = buscarNodoRecursivo(hijoIzquierdo, predecesor);
            eliminarRecursivo(nodoPredecesor, predecesor);
            return;
        }

        // Caso 2: si el izquierdo no puede intentamos con el sucesor
        if (hijoDerecho.getNumLlaves() > q) {
            int sucesor = obtenerSucesor(nodo, indice);

            nodo.setLlaveIndice(indice, sucesor);

            Nodo nodoSucesor = buscarNodoRecursivo(hijoDerecho, sucesor);
            eliminarRecursivo(nodoSucesor, sucesor);
            return;

        }

        // Caso 3: Ninguno tiene una llave de sobra, fusionamos
        Nodo fusionado = fusionar(nodo, indice);

        // la llave que queremos eliminar ahora se encuentra dentro del nodo fusionado
        eliminarRecursivo(fusionado, llave);

        // La fusión eliminó una llave del nodo padre,
        // así que ese padre también puede quedar subocupado.
        if (nodo == raiz) {
            repararRaiz();
        } else if (nodo.getNumLlaves() < q) {
            repararUnderflow(nodo);
        }
    }

    /**
     * Repara la subocupación de un nodo que contiene menos llaves
     * que el mínimo permitido.
     *
     * Primero intenta redistribuir utilizando el hermano izquierdo,
     * después el hermano derecho y, si ninguno puede prestar una llave,
     * realiza una fusión. La reparación puede propagarse hacia el padre.
     *
     * @param nodo nodo que presenta subocupación
     */
    private void repararUnderflow(Nodo nodo) {
        // Caso especial en la raiz
        if (nodo == raiz) {
            repararRaiz();
            return;
        }

        // Si ya cumple el minimo no hacemos nada
        if (nodo.getNumLlaves() >= q) {
            return;
        }

        Nodo padre = nodo.getPadre();
        int indiceNodo = padre.getHijos().indexOf(nodo);

        // 1. Intentar redistribuir con el hermano izquierdo
        if (indiceNodo > 0) {
            Nodo hermanoIzquierdo = padre.obtenerHijoIndice(indiceNodo - 1);

            if (hermanoIzquierdo.getNumLlaves() > q) {
                redistribuirDesdeIzquierda(nodo, hermanoIzquierdo, padre, indiceNodo);
                return;
            }
        }

        // 2. Si el izquierdo no pudo intentamos con el derecho
        if (indiceNodo < padre.getNumHijos() - 1) {
            Nodo hermanoDerecho = padre.obtenerHijoIndice(indiceNodo + 1);

            if (hermanoDerecho.getNumLlaves() > q) {
                redistribuirDesdeDerecha(nodo, hermanoDerecho, padre, indiceNodo);
                return;
            }
        }

        // 3. Ninguno puede prestar hay que fusionar
        if (indiceNodo > 0) {
            fusionar(padre, indiceNodo - 1);
        } else {
            fusionar(padre, indiceNodo);
        }

        // La fusión eliminó una llave del padre, así que revisamos ahora al padre.
        if (padre == raiz) {
            repararRaiz();
        } else if (padre.getNumLlaves() < q) {
            repararUnderflow(padre);
        }
    }

    /**
     * Repara una subocupación utilizando una llave del hermano izquierdo.
     * La última llave del hermano izquierdo sube al padre y la llave
     * separadora del padre baja al nodo subocupado.
     *
     * Si los nodos son internos, también se transfiere el último hijo
     * del hermano izquierdo.
     *
     * @param nodo             nodo que presenta subocupación
     * @param hermanoIzquierdo hermano izquierdo que presta una llave
     * @param padre            padre común de ambos nodos
     * @param indiceNodo       posición del nodo subocupado dentro del padre
     */
    private void redistribuirDesdeIzquierda(Nodo nodo, Nodo hermanoIzquierdo, Nodo padre, int indiceNodo) {
        int indiceSeparador = indiceNodo - 1;
        int llavePadre = padre.obtenLlave(indiceSeparador);

        int indiceUltimaLlave = hermanoIzquierdo.getNumLlaves() - 1;
        int llaveHermano = hermanoIzquierdo.obtenLlave(indiceUltimaLlave);

        // la llave del padre baja al nodo
        nodo.setLlave(llavePadre);
        nodo.ordenar();

        // la ultima llave del hermano sube al padre
        padre.setLlaveIndice(indiceSeparador, llaveHermano);

        // eliminamos del hermano la llave prestada
        hermanoIzquierdo.eliminarLlaveIndice(indiceUltimaLlave);

        // si no son hojas tambien debemos mover un hijo
        if (!hermanoIzquierdo.esHoja()) {
            int indiceHijo = hermanoIzquierdo.getNumHijos() - 1;

            Nodo hijoPrestado = hermanoIzquierdo.obtenerHijoIndice(indiceHijo);

            hermanoIzquierdo.eliminarHijoIndice(indiceHijo);
            nodo.agregarHijoAlInicio(hijoPrestado);

        }
    }

    /**
     * Repara una subocupación utilizando una llave del hermano derecho.
     * La primera llave del hermano derecho sube al padre y la llave
     * separadora del padre baja al nodo subocupado.
     *
     * Si los nodos son internos, también se transfiere el primer hijo
     * del hermano derecho.
     *
     * @param nodo           nodo que presenta subocupación
     * @param hermanoDerecho hermano derecho que presta una llave
     * @param padre          padre común de ambos nodos
     * @param indiceNodo     posición del nodo subocupado dentro del padre
     */
    private void redistribuirDesdeDerecha(Nodo nodo, Nodo hermanoDerecho, Nodo padre, int indiceNodo) {
        int indiceSeparador = indiceNodo;
        int llavePadre = padre.obtenLlave(indiceSeparador);

        int llaveHermano = hermanoDerecho.obtenLlave(0);

        // la llave del padre baja
        nodo.setLlave(llavePadre);
        nodo.ordenar();

        // la primera llave del hermano sube al padre
        padre.setLlaveIndice(indiceSeparador, llaveHermano);
        hermanoDerecho.eliminarLlaveIndice(0);

        // si son nodos internos el primer hijo del hermano derecho pasa al final del
        // nodo
        if (!hermanoDerecho.esHoja()) {
            Nodo hijoPrestado = hermanoDerecho.obtenerHijoIndice(0);
            hermanoDerecho.eliminarHijoIndice(0);

            nodo.setHijo(hijoPrestado);
            hijoPrestado.setPadre(nodo);
        }
    }

    /**
     * Fusiona dos hijos consecutivos de un mismo padre.
     *
     * La llave separadora del padre baja al hijo izquierdo. Después,
     * las llaves y los hijos del nodo derecho se transfieren al izquierdo.
     * Finalmente, el padre elimina la llave separadora y la referencia
     * al nodo derecho.
     *
     * @param padre           nodo padre que contiene la llave separadora
     * @param indiceSeparador índice de la llave que separa los dos hijos
     * @return nodo izquierdo resultante de la fusión
     */
    private Nodo fusionar(Nodo padre, int indiceSeparador) {
        Nodo izquierdo = padre.obtenerHijoIndice(indiceSeparador);
        Nodo derecho = padre.obtenerHijoIndice(indiceSeparador + 1);

        int separador = padre.obtenLlave(indiceSeparador);

        // la llave del padre baja al nodo izquierdo
        izquierdo.setLlave(separador);

        // pasamos todas las llaves del nodo derecho
        while (derecho.getNumLlaves() > 0) {
            int llave = derecho.obtenLlave(0);

            izquierdo.setLlave(llave);
            derecho.eliminarLlaveIndice(0);
        }
        izquierdo.ordenar();

        // si existen hijos tambien pasan la nodo fusionado
        while (derecho.getNumHijos() > 0) {
            Nodo hijo = derecho.obtenerHijoIndice(0);
            derecho.eliminarHijoIndice(0);

            izquierdo.setHijo(hijo);
            hijo.setPadre(izquierdo);
        }

        // el padre pierde la llave separadora
        padre.eliminarLlaveIndice(indiceSeparador);

        // y pierde el hijo derecho porque fue absorbido
        padre.eliminarHijoIndice(indiceSeparador + 1);

        return izquierdo;
    }

    /**
     * Repara el caso especial de una raíz que queda sin llaves.
     *
     * Si conserva un único hijo, dicho hijo se convierte en la nueva raíz
     * y la altura del árbol disminuye. Si no tiene hijos, el árbol queda
     * vacío.
     */
    private void repararRaiz() {
        if (raiz == null) {
            return;
        }
        // si todavia tiene llaves no hay nada que hacer
        if (raiz.getNumLlaves() > 0) {
            return;
        }

        // Raíz sin llaves y con un único hijo: el hijo se convierte en la nueva raíz
        if (raiz.getNumHijos() == 1) {
            Nodo nuevaRaiz = raiz.obtenerHijoIndice(0);
            nuevaRaiz.setPadre(null);

            raiz = nuevaRaiz;
            numNiveles--;
            return;
        }

        // raiz sin llaves y sin hijos el arbol queda vacio
        if (raiz.getNumHijos() == 0) {
            raiz = null;
            numNiveles = 0;
        }
    }

    /**
     * Imprime el Árbol B nivel por nivel, de izquierda a derecha.
     * Utiliza una cola para realizar un recorrido por amplitud y muestra
     * las llaves contenidas en cada nodo.
     */
    public void imprimirPorNiveles() {
        if (this.raiz == null) {
            System.out.println("Árbol vacío");
            return;
        }

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(this.raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int nodosNivel = cola.size();
            System.out.print("Nivel " + nivel + ": ");
            for (int i = 0; i < nodosNivel; i++) {
                Nodo nodoActual = cola.poll();
                nodoActual.imprimirNodo();

                if (i < nodosNivel - 1) {
                    System.out.print(" ");
                }

                if (!nodoActual.esHoja()) {
                    for (int j = 0; j < nodoActual.getNumHijos(); j++) {
                        cola.add(nodoActual.obtenerHijoIndice(j));
                    }
                }
            }
            System.out.println();
            nivel++;
        }
    }
}