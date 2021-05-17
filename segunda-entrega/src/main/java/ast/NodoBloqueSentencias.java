package ast;

import java.util.ArrayList;
import java.util.List;

public class NodoBloqueSentencias extends NodoSentencia {
    private List<NodoSentencia> sentencias;

    public NodoBloqueSentencias(){
        super("BODY");
        this.sentencias = new ArrayList<>();
    }

    public void add(NodoSentencia sentencia) {
        this.sentencias.add(sentencia);
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder();
        resultado.append(super.graficar(idPadre));
        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.graficar(miId));
        }
        return resultado.toString();
    }
}
