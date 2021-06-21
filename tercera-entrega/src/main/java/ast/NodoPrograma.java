package ast;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class NodoPrograma extends Nodo {
    private final List<NodoSentencia> sentencias;
    private final HashMap<String, List<String>> simbolos_tabla;

    public NodoPrograma(List<NodoSentencia> sentencias, HashMap<String, List<String>> simbolos_tabla) {
        super("PGM");
        this.sentencias = sentencias;
        this.simbolos_tabla = simbolos_tabla;
    }

    public String graficar() {
        // Acá se dispara la invocación a los métodos graficar() de los nodos.
        // Como un NodoPrograma no tiene padre, se inicia pasando null.
        return this.graficar(null);
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = "nodo_programa";

        StringBuilder resultado = new StringBuilder();
        resultado.append("graph G {");

        resultado.append(miId + " [label=\"Programa\"]\n");
        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.graficar(miId));
        }

        resultado.append("}");

        return resultado.toString();
    }

    public String generarAssembler() {
    	StringBuilder resultado = new StringBuilder();
        resultado.append(".MODEL\tLARGE\n");
        resultado.append(".386\n");
        resultado.append(".STACK 200h\n");
        resultado.append("\n");
        for (Map.Entry<String, List<String>> entry : simbolos_tabla.entrySet()) {
            String id = entry.getKey();
            String type = entry.getValue().get(0);
            String value = entry.getValue().size() > 1 ? entry.getValue().get(1) : null;
            switch (type) {
                case "CTE_INT":
                case "CTE_FLOAT":
                    resultado.append(String.format("\t%1$s\tdd\t%2$s\n", id, value));
                    break;
                case "CTE_STR":
                    resultado.append(String.format("\t%1$s\tdb\t%2$s,'$'\n", id.replaceAll(" ","_"), value));
                    break;
                default:
                    resultado.append(String.format("\t%s\tdb\t?\n", id));
                    break;
			}
        }
        resultado.append(".CODE\n");
        resultado.append("\n");
    	resultado.append("START:\n");
        resultado.append("\tmov ax, @DATA\n");
        resultado.append("\tmov ds, ax\n");
    	resultado.append("\tmov es, ax\n");
        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.generarAssembler());
        }
        resultado.append("\t;mov ah, 1\n");
        resultado.append("\t;int 21h\n");
    	resultado.append("\tmov ax, 4C00h\n");
        resultado.append("\tint 21h\n");
        resultado.append("END START\n");
        return resultado.toString();
    }
}

