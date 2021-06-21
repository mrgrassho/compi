package ast;

import java.util.Arrays;

public class NodoAsignacion extends NodoSentencia implements NodoAsignacionInterface {
    private final NodoIdentificador identificador;
    private final NodoExpresion expresion;

    public NodoAsignacion(NodoIdentificador identificador, NodoExpresion expresion) {
        super(":=");
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                identificador.graficar(miId) +
                expresion.graficar(miId);
    }

    @Override
    public String generarAssembler() {
    	return expresion.generarAssembler() +
    	        String.format("\tfstp %1$s\n", identificador.getVariable());
    }
}
