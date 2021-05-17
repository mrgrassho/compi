package ast;

public class NodoCondicionDoble extends NodoCondicion {
    private final NodoComparacion primeraComparacion;
    private final NodoComparacion segundaComparacion;

    public NodoCondicionDoble(String conector, NodoComparacion primeraComparacion, NodoComparacion segundaComparacion) {
        super(conector);
        this.primeraComparacion = primeraComparacion;
        this.segundaComparacion = segundaComparacion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                primeraComparacion.graficar(miId) +
                segundaComparacion.graficar(miId);
    }
}
