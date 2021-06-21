package ast;

import java.util.ArrayList;
import java.util.List;

public class NodoComparacion extends NodoExpresion {
    private final NodoExpresion izquierda;
    private final NodoExpresion derecha;
    private final String operador;
    private String asmOperador;

    public NodoComparacion(String operador, NodoExpresion izquierda, NodoExpresion derecha) {
        super("OP");
        this.operador = operador;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.asmOperador = this.getOperador();
    }

    public void setOperador(String asmOperador) {
        this.asmOperador = asmOperador;
    }

    @Override
    public String getDescripcionNodo() {
        return "OP: " + this.operador;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                izquierda.graficar(miId) +
                derecha.graficar(miId);
    }

    private String getOperador() {
    	String rta = "";
    	switch (this.operador) {
            case "==":
                rta = "jne";
                break;
            case "!=":
                rta = "je";
                break;
            case ">":
                rta = "jna";
                break;
            case "<":
                rta = "jnb";
                break;
            case ">=":
                rta = "jnae";
                break;
            case "<=":
                rta = "jnbe";
                break;
            }
    	return rta;
	}

    public String getOperadorInverso() {
    	String rta = "";
    	switch (this.operador) {
            case "==":
                rta = "je";
                break;
            case "!=":
                rta = "jne";
                break;
            case ">":
                rta = "ja";
                break;
            case "<":
                rta = "jb";
                break;
            case ">=":
                rta = "jae";
                break;
            case "<=":
                rta = "jbe";
                break;
        }
    	return rta;
	}
    
    public String generarAssembler(String endTag) {
    	StringBuilder lineas = new StringBuilder();
    	if (izquierda instanceof NodoHoja) {
    		lineas.append(derecha.generarAssembler());
			lineas.append(izquierda.generarAssembler());
		} else {
			lineas.append(izquierda.generarAssembler());
			lineas.append(derecha.generarAssembler());
			lineas.append("\tfxch\n");
		}
    	lineas.append("\tfcomp\n");
    	lineas.append("\tfstsw ax\n");
    	lineas.append("\tsahf\n");
    	lineas.append(String.format("\t%1$s %2$s\n", this.asmOperador, endTag));
    	return lineas.toString();
    }
}

