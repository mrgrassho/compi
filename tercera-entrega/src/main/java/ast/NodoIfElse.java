package ast;

import java.text.MessageFormat;

public class NodoIfElse extends NodoSentencia {
    private static int contador = 0;
    private NodoCondicion condicion;
    private NodoBloqueSentencias bodyIf;
    private NodoBloqueSentencias bodyIfElse;

	public NodoIfElse(NodoCondicion condicion, NodoBloqueSentencias bodyIf, NodoBloqueSentencias bodyIfElse) {
		this("IF_ELSE", condicion, bodyIf, bodyIfElse);
	}

    public NodoIfElse(String nombre, NodoCondicion condicion, NodoBloqueSentencias bodyIf, NodoBloqueSentencias bodyIfElse) {
        super(MessageFormat.format("{0}_{1}", nombre, ++contador));
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

    @Override
	public String generarAssembler() {
		String elseTag = null;
		if (bodyIfElse != null)
			elseTag = getDescripcionNodo() + "_ELSE";
		String endTag = getDescripcionNodo() + "_END";
		StringBuilder resultado = new StringBuilder();
		if (elseTag==null)
			resultado.append(condicion.generarAssembler(endTag));
		else
			resultado.append(condicion.generarAssembler(elseTag));
		for (NodoSentencia s : bodyIf.getSentencias()) {
			resultado.append(s.generarAssembler());
		}
		resultado.append(String.format("\tjmp %1$s\n", endTag));
		if (elseTag != null) {
			resultado.append(String.format("%1$s:\n", elseTag));
			for (NodoSentencia s : bodyIfElse.getSentencias()) {
				resultado.append(s.generarAssembler());
			}
		}
		resultado.append(String.format("%1$s:\n", endTag));
		return resultado.toString();
	}
}
