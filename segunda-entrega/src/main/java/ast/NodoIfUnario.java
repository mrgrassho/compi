package ast;

public class NodoIfUnario extends NodoExpresion {
    private final NodoCondicion condicion;
    private final NodoExpresion bodyIf;
    private final NodoExpresion bodyIfElse;

    public NodoIfUnario(NodoCondicion condicion, NodoExpresion bodyIf, NodoExpresion bodyIfElse) {
        super("IF UNARIO");
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

