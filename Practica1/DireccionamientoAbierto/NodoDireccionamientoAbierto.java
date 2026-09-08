
public class NodoDireccionamientoAbierto<V> {
    
    private int llave;
    private V valor;
    public boolean eliminado;

    
    public NodoDireccionamientoAbierto(int llave, V valor) {
	this.llave = llave;
	this.valor = valor;
	this.eliminado = false;
    }

    public int obtenerLlave() {
	return this.llave;
    }

    public V obtenerValor() {
	return this.valor;
    }

    public boolean estaEliminado() {
	return this.eliminado;
    }
    
    @Override
    public String toString() {
	return "(" + this.llave + ": "+ this.valor + ")";
    }
}
