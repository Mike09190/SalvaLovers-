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
        return this.numLlaves;
    }

    public ArrayList<Nodo> getHijos(){
        return this.hijos;
    }

    public Nodo obtenerHijoIndice(int indice){
        return this.hijos.get(indice);
    }
    public int getIndiceLlave(int llave){
        return this.llaves.indexOf(llave);
    }

    /**
     * Devuelve la posiciom de un hijo dentro del nodo
     * @param hijo 
     */
    public int getIndiceHijo(Nodo hijo){
        return this.hijos.indexOf(hijo);
    }


    /**
     * Setters
     */


    public void setLlave(int llave){
        this.llaves.add(llave);
        this.numLlaves++;
    }
    /**
     * Método para reemplazar una llave en un índice específico
     * @param int llave a reemplazar
     */
    public void setLlaveIndice(int indice, int llave){
        this.llaves.set(indice, llave);

    }

    /**
     * Inserta una nueva llave en un indice especifico.
     */
    public void insertarLlaveIndice(int indice, int llave){
        this.llaves.add(indice, llave);
        this.numLlaves--;
    }

    public void setHijo(Nodo hijo){
        this.hijos.add(hijo);
        this.numHijos++;
        this.esHoja = false;
        hijo.setPadre(this);
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
    for(int i=0; i<numLlaves-1; i++){
        for(int j = 0; j<numLlaves -i -1; j++){
            if(llaves.get(j) > llaves.get(j+1)){
                temporal = llaves.get(j);
                llaves.set(j, llaves.get(j+1));
                llaves.set(j+1, temporal);
            }
        }
    }

}
    /**
     * Método para buscar llave por llave dentro del nodo
     * 
     * @param int llave 
     */
    public boolean buscaLlave(int llave){
        return this.llaves.contains(llave);
    }

    /**
     * Método q regresa la llave de un elemento
     * @param int indice del valor a regresar
     * @return llave almacenada en el indice
     */
    public int obtenLlave(int indice){
        return this.llaves.get(indice);

    }

    /**
     * Indica si el nodo no contiene llaves.
     */
    public boolean estaVacio(){
        return this.numLlaves == 0;
    }
    
    /**
     * Método para borrar una llave del Nodo
     * @param llave a borrar
     */
    public void borraLlave(int llave){
        if (this.llaves.remove(Integer.valueOf(llave))){
            this.numLlaves--;
        }

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

    // metodos agregados para eliminar 

    /**
     * Elimina una llave mediante un indice
     *
     * @param indice de la llave a eliminar 
     */
    public void eliminarLlaveIndice(int indice){
        this.llaves.remove(indice);
        this.numLlaves--;
    }

    /**
     * Metodo que elimina un hijo por indice
     * 
     * @param indice 
     */
    public void eliminarHijoIndice(int indice){
        this.hijos.remove(indice);
        this.numHijos--;
        if(this.hijos.isEmpty()){
            this.esHoja = true;
        }
    }

    public void insertarHijoIndice(int indice, Nodo hijo){
        this.hijos.add(indice, hijo);
        this.numHijos++;
        this.esHoja = false;
        hijo.setPadre(this);
    }

    public void agregarHijoAlInicio(Nodo hijo){
        this.hijos.add(0, hijo);
        this.numHijos++;
        this.esHoja = false;
        hijo.setPadre(this);
    }

    /**
    * Imprime las llaves almacenadas en el nodo.
    */
    public void imprimirNodo(){
        System.out.print("[");
        for(int i = 0; i < this.numLlaves; i++){
            System.out.print(this.llaves.get(i));
            if(i < this.numLlaves - 1){
                System.out.print(" | ");
            }
        }
        System.out.print("]");
    }
}
