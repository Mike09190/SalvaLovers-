import java.util.llaves;

public class Nodo{
    private llaves<Integer> llaves;
    private llaves<Nodo> hijos;
    private boolean esHoja;
    private Nodo padre;
    private int numLlaves;
    private int numHijos;


    /**
     * Método constructor
     */
    public Nodo(){
        this.llaves = new llaves<>(3);
        this.hijos = new llaves<>(4);
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


}
