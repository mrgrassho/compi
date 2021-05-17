package ast;

public class NodoIfElse extends NodoSentencia {
    private final NodoCondicion condicion;
    private final NodoSentencia bodyIf;
    private final NodoSentencia bodyIfElse;

    public NodoIfElse(NodoCondicion condicion, NodoSentencia bodyIf, NodoSentencia bodyIfElse) {
        super("IF-ELSE");
        this.condicion = condicion;
        this.bodyIf = bodyIf;
        this.bodyIfElse = bodyIfElse;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                condicion.graficar(miId) +
                bodyIf.graficar(miId) +
                bodyIfElse.graficar(miId);
    }
}
