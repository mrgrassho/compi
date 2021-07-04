package ast;

public class NodoConstante extends NodoExpresion implements NodoHoja {
    private String valor;
    private String type;


    public NodoConstante(String type, String valor) {
        super("CTE");
        this.type = type;
        this.valor = valor.replaceAll("\'|\"", "").replaceAll("\\.", "_");
        if (this.valor.isEmpty()){
            this.valor = "cadena_vacia";
        }
    }

    @Override
    public String getDescripcionNodo() {
        return this.type + ":" + this.valor;
    }

    @Override
    public String generarAssembler() {
        switch (this.type) {
            case "CTE_FLOAT":
                return String.format("\tfld _%1$s\n", valor);
            case "CTE_BOOL":
            case "CTE_INT":
                return String.format("\tfild _%1$s\n", valor);
            case "CTE_STR":
            default:
                break;
    	}
    	return "";
    }
}
