package ast;

public class NodoComparacion extends NodoExpresion {
    private final NodoExpresion izquierda;
    private final NodoExpresion derecha;
    private final String operador;

    public NodoComparacion(String operador, NodoExpresion izquierda, NodoExpresion derecha) {
        super("OP");
        this.operador = operador;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public String getDescripcionNodo() {
        return "OP: " + this.operador;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                izquierda.graficar(miId) +
                derecha.graficar(miId);
    }
}

