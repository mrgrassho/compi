package ast;

public class NodoIf extends NodoIfElse {

    public NodoIf(NodoCondicion condicion, NodoBloqueSentencias bodyIf) {
        super("IF", condicion, bodyIf, null);
    }
}
