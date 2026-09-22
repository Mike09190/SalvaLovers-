public class ArbolB{

    private Nodo raiz;
    private int m;
    private int r;
    private int q;
    private int numNiveles;

    /**
     * Método constructor
     */

    public ArbolB(){
        this.raiz = null;
        this.m = 4;
        this.r = m-1;
        this.q = (m/2) - 1;
        this.numNiveles = 0; 
    }

    /**
     * Getters
     */

    public Nodo getRaiz(){
        return this.raiz;
    }

    public int getM(){
        return this.m;
    }

    public int getR(){
        return this.r;
    }

    public int getQ(){
        return this.q;
    }

    public int getNumNiveles(){
        return this.numNiveles;
    }
    /**
     * Método insertar
     * 
     * @param int llave
     */

    public void insertar(int llave){

        //Si no hay raíz
        if(this.raiz == null){
            Nodo nuevo = new Nodo();
            nuevo.setLlave(llave);
            this.raiz = nuevo;
            this.numNiveles = 1;
            return;
        }

        Nodo nodo = buscarNodo(llave);
        if(nodo.buscaLlave(llave)){
            return;
        }
        nodo.setLlave(llave);
        nodo.ordenar();
        if(nodo.getNumLlaves() > r){
            split(nodo);
        }
    }


    /**
     * Método auxiliar para hacer Split sobre el árbol B
     * @param Nodo nodo el cual se realiza split
     */
    private void split(Nodo nodo){
        
        int k3 = nodo.obtenLlave(2);

        Nodo padre = nodo.getPadre();
        Nodo nodo1 = new Nodo();
        Nodo nodo2 = new Nodo();

        nodo1.setLlave(nodo.obtenLlave(0));
        nodo1.setLlave(nodo.obtenLlave(1));

        nodo2.setLlave(nodo.obtenLlave(3));
    //Cuando el nodo del split no es hoja, osea tiene hijos
        if(!nodo.esHoja()){     
                 
            for(int i=0; i<2; i++){
                Nodo hijo = nodo.obtenerHijoIndice(i);

                if(hijo !=null){
                    nodo1.setHijo(hijo);
                    hijo.setPadre(nodo1);
                }
            }

            for(int j=2; j<4; j++){
                Nodo hijo = nodo.obtenerHijoIndice(j);

                if(hijo != null){
                    nodo2.setHijo(hijo);
                    hijo.setPadre(nodo2);
                }
            }
        }

        //Cuando tiene padre
        if(nodo.getPadre() != null){
            padre.setLlave(k3);
            padre.ordenar();
            padre.reemplazar(nodo, nodo1, nodo2);
            if(padre.getNumLlaves() > r){
                split(padre); //Hacemos recursión en caso de que el padre requiera un split
            }
        }
        
           //Cuando no tiene padre
        else{
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
     * Método que busca una llave dentro del Árbol B, primero comparando el elemento con el Nodo actual y luego con sus hijos si no es hoja
     * @param int llave a buscar
     * @return true si la llave sí está dentro del árbol B y false si no lo encuentra
     */
    public boolean buscar(int llave){
        Nodo nodoActual = this.raiz;

        // Caso base árbol vacío
        if (nodoActual == null) {
            return false;
        }

        //Usamos el método auxiliar para buscar el nodo donde se encuentra la llave
        Nodo nodo = buscarNodo(llave);
        if (nodo != null && nodo.buscaLlave(llave)){
            return true;
        }
        return false;
}
    /**
     * Método recursivo que busca el Nodo donde se puede insertar un elemento
     * @param key llave a insertar
     * @return Nodo el Nodo donde puede ir el elemento
     */
    private Nodo buscarNodo(int key){
        return buscarNodoRecursivo(this.raiz, key);
    }

    /**
     * Método auxiliar que devuelve el Nodo donde se puede insertar un elemento
     * @param Nodo nodo donde se busca si es hoja
     * @param key llave a insertar
     * @return Nodo el Nodo donde puede ir el elemento
     */
    private Nodo buscarNodoRecursivo(Nodo nodoActual, int key){
        //Caso base 1. La raíz tiene menos de r elementos
        if(nodoActual.esHoja()){
            return nodoActual;
        }
        //Caso base 2, el nodo ya posee al elemento que se quiere insertar
        if(nodoActual.buscaLlave(key)){
            return nodoActual;
        }

        //Recursividad bajar a sus hijos por condiciones
        int indice = 0;
        while(indice < nodoActual.getNumLlaves() && key > nodoActual.obtenLlave(indice)){
            indice ++;
        }
        Nodo hijo = nodoActual.obtenerHijoIndice(indice); 
        return buscarNodoRecursivo(hijo, key);  
    }


    public void eliminar(int llave){

        //Verificar si existe un árbol
        if(this.raiz == null){
            return;
        }
        Nodo nodo = buscarNodo(llave); //Devuelve el nodo donde se puede encontrar la llave

        // Si la llave no existe, no modificamos nada.
        if(nodo == null || !nodo.buscaLlave(llave)){
            return;
        }

        //Llamamos al método recursivo para eliminar una llave
        eliminarRecursivo(nodo, llave);
    }
 
    /**
     * Método recursivo para eliminar una llave del árbol B
     * @param nodo
     * @param indice
     */
    private void eliminarRecursivo(Nodo nodo, int llave){

        //Caso 1: La llave esta en una hoja
        if(nodo.esHoja()){
            nodo.borraLlave(llave);
            
            // Si la raiz tiene tratamiento especial
            if(nodo == raiz){
                repararRaiz();
                return;
            }

            // Si quedo por debajo del minimo
            if(nodo.getNumLlaves() < q){
                repararUnderflow(nodo);
            }
            return;
        }
        
        // Caso 2: la llave esta en un nodo interno.
        int indice = nodo.getIndiceLlave(llave);
        eliminarInterno(nodo, indice);
        
    }


    /**
     * Método auxiliar que obtiene el predecesor de un nodo
     * @param nodo nodo del que va a buscar su hijo izq el predecesor
     * @param indice indice de llave del nodo
     * @return int llave del predecesor
     */
    private int obtenerPredecesor(Nodo nodo, int indice){

        Nodo hijo = nodo.obtenerHijoIndice(indice);
        while (!hijo.esHoja()) {
        hijo = hijo.obtenerHijoIndice(hijo.getNumHijos() - 1);
        }
        return hijo.obtenLlave(hijo.getNumLlaves() - 1);
    }

    /**
     * Método auxiliar que obtiene el sucesor de un nodo
     * @param nodo nodo del que va a buscar su hijo der el sucesor
     * @param indice indice de llave del nodo
     * @return int llave del sucesor
     */
    private int obtenerSucesor(Nodo nodo, int indice){
        Nodo hijo = nodo.obtenerHijoIndice(indice + 1);

        while (!hijo.esHoja()) {
            hijo = hijo.obtenerHijoIndice(0);
        }
        return hijo.obtenLlave(0);
    }


    private void eliminarInterno(Nodo nodo, int indice){
        int llave = nodo.obtenLlave(indice);

        Nodo hijoIzquierdo = nodo.obtenerHijoIndice(indice);
        Nodo hijoDerecho = nodo.obtenerHijoIndice(indice + 1);

        // Caso 1: intentamos primero con el predecesor
        if(hijoIzquierdo.getNumLlaves() > q){
            int predecesor = obtenerPredecesor(nodo, indice);

            // Sustituimos la llave interna
            nodo.setLlaveIndice(indice, predecesor);

            Nodo nodoPredecesor = buscarNodoRecursivo(hijoIzquierdo, predecesor);
            eliminarRecursivo(nodoPredecesor, predecesor);
            return;
        }

        // Caso 2: si el izquierdo no puede intentamos con el sucesor
        if(hijoDerecho.getNumLlaves() > q){
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
        if(nodo == raiz){
            repararRaiz();
        }else if(nodo.getNumLlaves() < q){
            repararUnderflow(nodo);
        }
    }

    /**
     * 
     */
    private void repararUnderflow(Nodo nodo){
        // Caso especial en la raiz
        if(nodo == raiz){
            repararRaiz();
            return;
        }
        
        // Si ya cumple el minimo no hacemos nada
        if(nodo.getNumLlaves() >= q){
            return;
        }

        Nodo padre = nodo.getPadre();
        int indiceNodo = padre.getHijos().indexOf(nodo);

        // 1. insertar con el hermano izquierod
        if(indiceNodo > 0){
            Nodo hermanoIzquierdo = padre.obtenerHijoIndice(indiceNodo - 1);

            if(hermanoIzquierdo.getNumLlaves() > q){
                redistribuirDesdeIzquierda(nodo, hermanoIzquierdo, padre, indiceNodo);
                return;
            }
        }

        // 2. Si el izquierdo no pudo intentamos con el derecho
        if(indiceNodo < padre.getNumHijos()-1){
            Nodo hermanoDerecho = padre.obtenerHijoIndice(indiceNodo + 1);

            if( hermanoDerecho.getNumLlaves() > q){
                redistribuirDesdeDerecha(nodo, hermanoDerecho, padre, indiceNodo);
                return;
            }
        }

        // 3. Ninguno puede prestar hay que fusionar
        if(indiceNodo > 0){
            fusionar(padre, indiceNodo - 1);
        } else {
            fusionar(padre, indiceNodo);
        }

        // La fusión eliminó una llave del padre, así que revisamos ahora al padre.
        if(padre == raiz){
            repararRaiz();
        }else if(padre.getNumLlaves() < q){
            repararUnderflow(padre);
        }
    }

    /**
     * 
     */
    private void redistribuirDesdeIzquierda(Nodo nodo, Nodo hermanoIzquierdo, Nodo padre, int indiceNodo){
        int indiceSeparador = indiceNodo -1;
        int llavePadre = padre.obtenLlave(indiceSeparador);

        int indiceUltimaLlave = hermanoIzquierdo.getNumLlaves() -1;
        int llaveHermano = hermanoIzquierdo.obtenLlave(indiceUltimaLlave);

        // la llave del padre baja al nodo
        nodo.setLlave(llavePadre);
        nodo.ordenar();

        // la ultima llave del hermano sube al padre
        padre.setLlaveIndice(indiceSeparador, llaveHermano);

        // eliminamos del hermano la llave prestada
        hermanoIzquierdo.eliminarLlaveIndice(indiceUltimaLlave);

        // si no son hojas tambien debemos mover un hijo
        if(!hermanoIzquierdo.esHoja()){
            int indiceHijo = hermanoIzquierdo.getNumHijos() -1;

            Nodo hijoPrestado = hermanoIzquierdo.obtenerHijoIndice(indiceHijo);

            hermanoIzquierdo.eliminarHijoIndice(indiceHijo);
            nodo.agregarHijoAlInicio(hijoPrestado);

        }
    }

    /**
     * 
     */
    private void redistribuirDesdeDerecha(Nodo nodo, Nodo hermanoDerecho, Nodo padre, int indiceNodo){
        int indiceSeparador = indiceNodo;
        int llavePadre = padre.obtenLlave(indiceSeparador);

        int llaveHermano = hermanoDerecho.obtenLlave(0);

        // la llave del padre baja
        nodo.setLlave(llavePadre);
        nodo.ordenar();

        // la primera llave del hermano sube al padre
        padre.setLlaveIndice(indiceSeparador, llaveHermano);
        hermanoDerecho.eliminarLlaveIndice(0);

        // si son nodos internos el primer hijo del hermano derecho pasa al final del nodo
        if(!hermanoDerecho.esHoja()){
            Nodo hijoPrestado = hermanoDerecho.obtenerHijoIndice(0);
            hermanoDerecho.eliminarHijoIndice(0);

            nodo.setHijo(hijoPrestado);
            hijoPrestado.setPadre(nodo);
        }
    }

    /**
     * 
     */
    private Nodo fusionar(Nodo padre, int indiceSeparador){
        Nodo izquierdo = padre.obtenerHijoIndice(indiceSeparador);
        Nodo derecho = padre.obtenerHijoIndice(indiceSeparador + 1);

        int separador = padre.obtenLlave(indiceSeparador);

        // la llave del padre baja al nodo izquierdo
        izquierdo.setLlave(separador);

        // pasamos todas las llaves del nodo derecho
        while(derecho.getNumLlaves() > 0){
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
     * 
     */
    private void repararRaiz(){
        if(raiz == null){
            return;
        }
        // si todavia tiene llaves no hay nada que hacer
        if(raiz.getNumLlaves() > 0){
            return;
        }
        /**
         * Raiz con llave pero con un hijo 
         * ese hijo se convierte en la nueva llave
         */
        if(raiz.getNumHijos() == 1){
            Nodo nuevaRaiz = raiz.obtenerHijoIndice(0);
            nuevaRaiz.setPadre(null);

            raiz = nuevaRaiz;
            numNiveles--;
            return;
        }

        // raiz sin llaves y sin hijos el arbol queda vacio
        if(raiz.getNumHijos() == 0){
            raiz = null;
            numNiveles = 0;
        }
    }
}

