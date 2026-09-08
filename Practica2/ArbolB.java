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
        // caso dos 2, aun hay llaves disponibles
        if(this.raiz.getNumLlaves() < r){
            this.raiz.setLlave(llave);
            this.raiz.ordenar();
            return;
        }

        if(llave );


        if(nuevo.equals(this.raiz)){
            nuevo.setLlave(llave);
        }


        nuevo.setLlave(llave);

    }

        /**
     * Método auxiliar para hacer Split sobre el árbol B
     * @param Nodo nodo el cual se realiza split
     * @return true Si hizo bien el split
     */
    private boolean split(Nodo nodo){
        int k3 = nodo.obtenLlave(2);
        Nodo nodo1 = new Nodo();
        Nodo nodo2 = new Nodo();

        nodo1.setLlave(nodo.obtenLlave(0));
        nodo1.setLlave(nodo.obtenLlave(1));

        nodo2.setLlave(nodo.obtenLlave(3));
        if(nodo.getPadre != null){
            Nodo padre = nodo.getPadre;
            padre.setLlave(k3);
            padre.setHijo(nodo1);
            padre.setHijo(nodo2);
        }

    }

    /**
     * Método que busca una llave dentro del Árbol B, primero comparando el elemento con el Nodo actual y luego con sus hijos si no es hoja
     * @param int llave a buscar
     * @return true si la llave sí está dentro del árbol B y false si no lo encuentra
     */
    public boolean buscar(int llave){
        Nodo nodoActual = this.raiz;

        //Empezamos en raíz
        if(nodoActual.buscaLlave(llave)){
            return true;
        }

        // Si el nodo actual es hoja y no está la llave buscada, regresa false
            if(nodoActual.esHoja()){
            return false;
            }
        // Si no está en la raíz, que busque en sus hijos
        //Si el elemento es menor que la primera llave, se baja al hijo 0
        
        if(nodoActual.obtenLlave(0) > llave){
            Nodo nodoH1 = nodoActual.getHijos().get(0);
            return nodoH1.buscar(llave);
        }    

        //Si el elemento es mayor que la primera llave y menor que la segunda llave, se baja al hijo 1
        if(nodoActual.obtenLlave(0) < llave && llave < nodoActual.obtenLlave(1)){
            Nodo nodoH2 = nodoActual.getHijos().get(1);
            return nodoH2.buscar(llave);
        }
        
        //Si el elemento es mayor que la segunda llave y mayor que la segunda llave, se baja al hijo 2
        if(nodoActual.obten(1) < llave && llave < nodoActual(2)){
            Nodo nodoH3 = nodoActual.getHijos().get(2);
            return nodoH3.buscar(llave);
        }

        //Si el elemento es mayor que la tercera llave, se baja al hijo 3
        if(nodoActual.obten(2) < llave){
            Nodo nodoH4 = nodoActual.getHijos().get(3);
            return nodoH4.buscar(llave);
        }
        return false;

    }

}

}
