import java.util.ArrayList;

public class Nodo{
    private ArrayList<Integer> llaves;
    private ArrayList<Nodo> hijos;
    private boolean esHoja;
    private Nodo padre;
    private int numLlaves;
    private int numHijos;


    /**
     * Método constructor
     */
    public Nodo(){
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

    public boolean esHoja(){
        return this.esHoja;
    }

    public Nodo getPadre(){
        return this.padre;
    }

    public int getNumHijos(){
        return this.numHijos;
    }

    public int getNumLlaves(){
        return this.numLlaves();
    }

    public ArrayList<Nodo> getHijos(){
        return this.hijos;
    }

    public Nodo obtenerHijoIndice(int indice){
        this.hijos.get(indice);
    }

    /**
     * Setters
     */
    public void setLlave(int llave){
        this.llaves.add(llave);
        this.numLlaves++;
    }

    public void setHijo(Nodo hijo){
        this.hijos.add(hijo);
        this.numHijos++;
        this.esHoja = false;
    }

    public void setPadre(Nodo padre){
        this.padre = padre;
    }
        /**
     * Método auxiliar para ordenar llaves de llaves
     * 
     */
    public void ordenar(){
    int temporal;
    for(int i=0; i<r-1; i++){
        for(int j = 0; j<r -i -1; j++){
            if(llaves.get(i) > llaves.get(j+1)){
                temporal = llaves.get(j);
                llaves.set(j, llaves.get(j+1));
                llaves.set(j+1, temporal);
            }
        }
    }

}
/**
     * Método para buscar llave por llave dentro del nodo
     */
    public boolean buscaLlave(int llave){

        for(int i=0; i < 3 ; i++){
            if(this.llaves.indexOf(i) == llave){
                return true;
            }
        }
        return false;
    }

    /**
     * Método q regresa la llave de un elemento
     * @param int indice del valor a regresar
     */
    public int obtenLlave(int indice){
        this.llave.get(indice);

    }
    /**
     * Método para reemplazar los hijos anteriores de un Nodo y poner el nuevo
     * @param Nodo nodo hijo a eliminar
     * @param Nodo nodo1 a insertar
     * @param Nodo nodo2 a insertar
     */
    public void reemplazar(Nodo nodo, Nodo nodo1, Nodo nodo2){
        int pos = this.hijos.indexOf(nodo);
        Nodo padre = nodo.getPadre();
        this.hijos.remove(nodo);
        numHijos--;

        this.hijos.add(pos, nodo1);
        this.hijos.add(pos+1, nodo2);

        numHijos += 2;

        nodo1.setPadre(padre);
        nodo2.setPadre(padre);
    }

}
