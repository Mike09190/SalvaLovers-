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
        //Caso 1: El nodo es una hoja, por lo tanto solo eliminamos la llave
        if(nodo.esHoja()){
            nodo.borraLlave(llave);
        }
        //Case 2: El nodo no es una hoja, por lo que debemos ver si su predecesor o sucedor pueden donar
        if(!nodo.esHoja()){
            //Verificamos la carga de los hijos
            int indice = nodo.getIndiceLlave(llave);

            Nodo hijoIzquierdo = nodo.obtenerHijoIndice(indice);
            Nodo hijoDerecho = nodo.obtenerHijoIndice(indice + 1);

            int carga = 1;
            if(hijoIzquierdo != null && hijoIzquierdo.getNumLlaves() > carga){
                int predecesor = obtenerPredecesor(nodo, indice);
                nodo.setLlaveIndice(indice, predecesor);
                eliminarRecursivo(hijoIzquierdo, predecesor);
            }
            else if(hijoDerecho != null && hijoDerecho.getNumLlaves() > carga){
                int sucesor = obtenerSucesor(nodo, indice);
                nodo.setLlaveIndice(indice, sucesor);
                eliminarRecursivo(hijoDerecho, sucesor);
            }
            else{
               //Este método aun no existe xdxd fusionar(nodo, indice);
                eliminarRecursivo(hijoIzquierdo, llave);
            }
        }
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
        Nodo izquierdo = nodo.obtenerHijoIndice(indice);

        // Primero intenramos utilizar el predecesor
        if(izquierdo.getNumLlaves() > q ){
            Nodo predecesor = obtenerPredecesor(izquierdo);
            int llavePredecesora = predecesor.obtenLlave(predecesor.getNumHijos() - 1);

            nodo.reemplazar();

            eliminarLlaveHoja(predecesor, llavePredecesora);
            return;
        }
        // si no se puede intentamos con el sucesor 
        Nodo derecho = nodo.obtenerHijoIndice(indice + 1);

        if(derecho.getNumLlaves() > q){
            Nodo sucesor = obtenerSucesor(derecho);
            int llaveSucesor = sucesor.obtenLlave(0);

            nodo.reemplazar();

            eliminarLlaveHoja(sucesor, llaveSucesor);
            return;
        }
        // ninguno puede prestar, por lo cual fusionamos
        fusionar(nodo, indice);

    }
}

