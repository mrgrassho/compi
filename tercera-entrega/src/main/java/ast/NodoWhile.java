package ast;

import java.util.Arrays;
import java.util.List;

public class NodoWhile extends NodoSentencia {
    private static int contadorWhile = 0;
    private final NodoCondicion condicion;
    private final NodoBloqueSentencias body;

    public NodoWhile(NodoCondicion condicion, NodoBloqueSentencias body) {
        super("WHILE_" + ++contadorWhile);
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

    public String generarAssembler() {
		String startTag = getDescripcionNodo() + "_START";
		String endTag = getDescripcionNodo() + "_END";
        StringBuilder resultado = new StringBuilder();
        resultado.append(String.format("%1$s:\n", startTag));
        resultado.append(condicion.generarAssembler(endTag));
        for (NodoSentencia sentencia : this.body.getSentencias()) {
            resultado.append(sentencia.generarAssembler());
        }
        resultado.append(String.format("\tjmp %1$s\n", startTag));
        resultado.append(String.format("%1$s:\n", endTag));
        return resultado.toString();
	}
}
