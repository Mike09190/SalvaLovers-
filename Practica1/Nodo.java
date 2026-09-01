public class Nodo<V> {
    private int llave;
    private V valor;

    public Nodo(int llave, V valor) {
	this.llave = llave;
	this.valor = valor;
    }

    public int obtenerLlave() {
	return this.llave;
    }

    public V obtenerValor() {
	return this.valor;
    }

    @Override
    public String toString() {
	return "(" + this.llave + this.valor + ")";
    }
}