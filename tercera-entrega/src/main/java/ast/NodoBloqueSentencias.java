package ast;

import java.util.ArrayList;
import java.util.List;

public class NodoBloqueSentencias extends NodoSentencia {
    private List<NodoSentencia> sentencias;

    public NodoBloqueSentencias(){
        super("BODY");
        this.sentencias = new ArrayList<>();
    }

    public NodoBloqueSentencias(List list){
        super("BODY");
        this.sentencias = list;
    }

    public NodoBloqueSentencias(List list, String name){
        super(name);
        this.sentencias = list;
    }

    public void add(NodoSentencia sentencia) {
        this.sentencias.add(sentencia);
    }

    public List<NodoSentencia> getSentencias() {
        return sentencias;
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

    @Override
    public String generarAssembler() {
        final String miId = this.getIdNodo();
        StringBuilder resultado = new StringBuilder();
        for (NodoSentencia sentencia : this.sentencias) {
            resultado.append(sentencia.generarAssembler());
        }
        return resultado.toString();
    }
}
