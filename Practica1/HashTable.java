import java.util.LinkedList;

public class HashTable {

    int tamano = 7;
    LinkedList<Nodo<?>> [] htabla = (LinkedList<Nodo<?>>) new LinkedList[tamano];

    public HashTable(int tamano) {
	this.tamano = tamano;
	
	this.LinkedList<Nodo<?>> [] htable = htable;
	for(int i = 0; i<htable.length; i++) {
	    htable[i] = new LinkedList<>();
	}
    }

    public int hashFunction(int k) {
	return (k%7);
    }

    public void insertar(int key, V value) {
	htable[hashFunction(k)].add(new Nodo<>(k, value));
    }
}