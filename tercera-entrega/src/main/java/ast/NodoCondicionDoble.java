package ast;

public class NodoCondicionDoble extends NodoCondicion {
    private final NodoComparacion primeraComparacion;
    private final NodoComparacion segundaComparacion;
    private String conector;
    private static int contador = 0;

    public NodoCondicionDoble(String conector, NodoComparacion primeraComparacion, NodoComparacion segundaComparacion) {
        super(conector + "_" + ++contador);
        this.primeraComparacion = primeraComparacion;
        this.segundaComparacion = segundaComparacion;
        this.conector = conector;
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                primeraComparacion.graficar(miId) +
                segundaComparacion.graficar(miId);
    }

    @Override
    public String generarAssembler(String endTag) {
        String tag = getDescripcionNodo();
        switch (this.conector) {
            case "AND":
                return this.primeraComparacion.generarAssembler(endTag) +
                       this.segundaComparacion.generarAssembler(endTag);
            case "OR":
                return this.primeraComparacion.generarAssembler(tag) +
                       String.format("%1$s:\n", tag) +
                       this.segundaComparacion.generarAssembler(endTag);
    	}
    	return "";
    }
}
