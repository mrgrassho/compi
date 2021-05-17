package ast;

public class NodoWhile extends NodoSentencia {
    private final NodoCondicion condicion;
    private final NodoBloqueSentencias body;

    public NodoWhile(NodoCondicion condicion, NodoBloqueSentencias body) {
        super("WHILE");
        this.condicion = condicion;
        this.body = body;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                condicion.graficar(miId) +
                body.graficar(miId);
    }
}
