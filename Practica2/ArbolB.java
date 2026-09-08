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
     */

    public void insertar(int llave){
         //revisar que si sean ints
        Nodo nuevo = new Nodo();

        //Si no hay raíz
        if(this.raiz == null){
            nuevo.setLlave(llave);
            this.raiz = nuevo;
        }

        //Si el núm
        if()
        

        if(nuevo.equals(this.raiz)){
            nuevo.setLlave(llave);
        }


        nuevo.setLlave(llave);

    }

    /**
     * Método auxiliar para ordenar ArrayList de llaves
     * 
     * @param ArrayList a ordenar
     */
    private void ordenar(ArrayList<Integer> arrayList){
    int temporal;
    for(int i=0; i<r-1; i++){
        for(int j = 0; j<r -i -1; i++){
            if(arrayList.get(i) > arrayList.get(j+1)){
                temporal = arrayList.get(j);
                arrayList.set(j, arrayList.get(j+1));
                arrayList.set(j+1, temporal)
            }
        }
    }




}