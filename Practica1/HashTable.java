import java.util.LinkedList;

public class HashTable {

    private int cantidadElementos;
    private int tamano = 7;
    private LinkedList<Nodo<?>>[] htabla;

    public HashTable(int tamano) {
        this.tamano = tamano;
        this.cantidadElementos = 0;

        this.htabla = (LinkedList<Nodo<?>>[]) new Linkedlist<>();

        for (int i = 0; i < htable.length; i++) {
            htabla[i] = new LinkedList<>();
        }
    }

    public int hashFuncion(int k) {
        return (k % 7);
    }

    public void insertar(Nodo<?> nodo) {
        if (busqueda(nodo.obtenerLlave()) == false) {
            htable[hashFuncion(nodo.obtenerLlave())].add(nodo);
            cantidadElementos++;
        } else {
            eliminar(nodo.obtenerLlave());
            htable[hashFuncion(nodo.obtenerLlave())].add(nodo);
        }
    }

    public boolean busqueda(int key) {
        int indice = hashFunction(key);
        LinkedList<Nodo<?>> listaBuscar = htable[indice];

        for (Nodo<V> v : listaBuscar) {
            if (v.obtenerLlave() == key) {
                return true;
            }
            return false;
        }
    }

    public boolean eliminar(int key) {
        int indice = hashFunction(key);
        if (busqueda(key)) {
            LinkedList<Nodo<?>> listaEliminar = htable[indice];
            listaEliminar.remove(key);
            cantidadElementos--;
            return true;
        }
        return false;
    }

    public double factorCarga() {
        return (double) cantidadElementos / tamano;
    }


    public String imprimirTabla(){
        System.out.println(" ---- TABLA HASH PRO -----");
        for(int i = 0; i < 7; i++){
            System.out.print(i + " -> ");
            LinkedList<Nodo<?>> lista = htable[i];
            for(Nodo<?> n : lista){
            System.out.print(Nodo.toString + " -> ");
            }
        System.out.println();
        }
    }

}
