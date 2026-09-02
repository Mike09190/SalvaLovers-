import java.util.LinkedList;

public class HashTable {

    private int cantidadElementos;
    private int tamano = 7;
    private LinkedList<Nodo<?>>[] htabla;

    public HashTable(int tamano) {
        this.tamano = tamano;
        this.cantidadElementos = 0;

        this.htabla = (LinkedList<Nodo<?>>[]) new LinkedList[tamano];

        for (int i = 0; i < htabla.length; i++) {
            htabla[i] = new LinkedList<>();
        }
    }

    public int hashFuncion(int k) {
        return (k % 7);
    }

    public void insertar(Nodo<?> nodo) {
        if (busqueda(nodo.obtenerLlave()) == false) {
            htabla[hashFuncion(nodo.obtenerLlave())].add(nodo);
            cantidadElementos++;
        } else {
            eliminar(nodo.obtenerLlave());
            htabla[hashFuncion(nodo.obtenerLlave())].add(nodo);

        }
    }

    public boolean busqueda(int key) {
        int indice = hashFuncion(key);
        LinkedList<Nodo<?>> listaBuscar = htabla[indice];

        for (Nodo<?> v : listaBuscar) {
            if (v.obtenerLlave() == key) {
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int key) {
        int indice = hashFuncion(key);
        if (busqueda(key)) {
            LinkedList<Nodo<?>> listaEliminar = htabla[indice];
            for (Nodo<?> v : listaEliminar) {
                if (v.obtenerLlave() == key) {
                    listaEliminar.remove(v);
                    cantidadElementos--;
                    return true;
                }
            }
        }
        return false;
    }

    public double factorCarga() {
        return (double) cantidadElementos / tamano;
    }

    public void imprimirTabla() {
        System.out.println(" ---- TABLA HASH PRO -----");
        for (int i = 0; i < 7; i++) {
            System.out.print(i + " -> ");
            LinkedList<Nodo<?>> lista = htabla[i];
            for (Nodo<?> n : lista) {
                System.out.print(n.toString() + " -> ");
            }
            System.out.println();
        }
    }
}
