package ast;

public class NodoIdentificador extends NodoExpresion {
    private final String identificador;
    private final String type;

    public NodoIdentificador(String identificador, String type) {
        super("ID");
        this.identificador = identificador;
        this.type = type;
    }

    @Override
    public String getDescripcionNodo() {
        return "ID: " + identificador + "\nTYPE: " + type;
    }
}
