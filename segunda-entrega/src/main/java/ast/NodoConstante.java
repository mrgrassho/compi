package ast;

public class NodoConstante extends NodoExpresion {
    private String valor;
    private String type;

    public NodoConstante(String type, String valor) {
        super("CTE");
        this.type = type;
        this.valor = valor;
    }

    @Override
    public String getDescripcionNodo() {
        return this.type + ":" + this.valor;
    }
}
