package ast;

public class NodoIf extends NodoIfElse {
    private NodoCondicion condicion;
    private NodoBloqueSentencias bodyIf;

    public NodoIf(NodoCondicion condicion, NodoBloqueSentencias bodyIf) {
        super("IF", condicion, bodyIf, null);
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                condicion.graficar(miId) +
                bodyIf.graficar(miId);
    }
}
