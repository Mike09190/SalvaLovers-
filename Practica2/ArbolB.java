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



}