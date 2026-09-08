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

}