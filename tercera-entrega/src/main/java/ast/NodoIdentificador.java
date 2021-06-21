package ast;

public class NodoIdentificador extends NodoExpresion implements NodoHoja {
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

    public String getVariable() {
    	return this.identificador;
    }
    
    @Override
    public String generarAssembler() {
    	return String.format("\tfld %1$s\n", identificador);
    }
}
