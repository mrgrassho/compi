package ast;

public class NodoCondicionSimple extends NodoCondicion{
    private final NodoComparacion comparacion;

    public NodoCondicionSimple(String nombre, NodoComparacion comparacion) {
        super(nombre);
        this.comparacion = comparacion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                comparacion.graficar(miId);
    }
}
