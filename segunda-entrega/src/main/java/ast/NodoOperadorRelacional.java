package ast;

public class NodoOperadorRelacional extends Nodo {
    private final String identificador;

    public NodoOperadorRelacional(String identificador) {
        super("OP");
        this.identificador = identificador;
    }

    @Override
    public String getDescripcionNodo() {
        return "OP: " + identificador;
    }
}
