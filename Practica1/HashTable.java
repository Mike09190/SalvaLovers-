import java.util.LinkedList;

public class HashTable {

    private int tamano = 7;
    private LinkedList<Nodo<?>> [] htabla;

    public HashTable(int tamano) {
	this.tamano = tamano;
	
	this.htabla = (LinkedList<Nodo<?>> []) new Linkedlist<>();
	
	for(int i = 0; i < htable.length; i++) {
	    htabla[i] = new LinkedList<>();
	}
    }

    public int hashFuncion(int k) {
	return (k%7);
    }

    public void insertar(Nodo<> nodo) {
	if(htabla.buscar() == false){
	    htable[hashFuncion(nodo.obtenerLlave())].add(nodo);
	}
    }

    
}
