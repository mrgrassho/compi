package ast;

public class NodoComparacion extends NodoExpresion {
    private final NodoExpresion izquierda;
    private final NodoExpresion derecha;

    public NodoComparacion(String operador, NodoExpresion izquierda, NodoExpresion derecha) {
        super(operador);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                izquierda.graficar(miId) +
                derecha.graficar(miId);
    }
}

