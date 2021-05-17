package ast;

public class NodoIf extends NodoSentencia {
    private final NodoCondicion condicion;
    private final NodoSentencia bodyIf;

    public NodoIf(NodoCondicion condicion, NodoSentencia bodyIf) {
        super("IF");
        this.condicion = condicion;
        this.bodyIf = bodyIf;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                condicion.graficar(miId) +
                bodyIf.graficar(miId);
    }
}
