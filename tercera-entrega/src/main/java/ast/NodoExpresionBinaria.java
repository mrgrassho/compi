package ast;

public class NodoExpresionBinaria extends NodoExpresion {
    private final NodoExpresion izquierda;
    private final NodoExpresion derecha;
    private static int contador = 0;
    private int actualContador;

    public NodoExpresionBinaria(String nombre, NodoExpresion izquierda, NodoExpresion derecha) {
        super(nombre);
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.actualContador = ++contador;
    }

    public String getAuxVariable() {
        return String.format("_@aux%1$s", String.valueOf(this.actualContador));
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        return super.graficar(idPadre) +
                izquierda.graficar(miId) +
                derecha.graficar(miId);
    }

    @Override
    public String generarAssembler() {
        if (izquierda instanceof NodoHoja && derecha instanceof NodoHoja) {
            return izquierda.generarAssembler() +
                    derecha.generarAssembler();
        } else if (izquierda instanceof NodoExpresionBinaria && derecha instanceof NodoExpresionBinaria) {
            return derecha.generarAssembler() +
                    izquierda.generarAssembler() +
                    String.format("\tfld %1$s\n", ((NodoExpresionBinaria) izquierda).getAuxVariable()) +
                    String.format("\tfld %1$s\n", ((NodoExpresionBinaria) derecha).getAuxVariable());
        } else if (derecha instanceof NodoExpresionBinaria) {
            return derecha.generarAssembler() +
                    izquierda.generarAssembler() +
                    String.format("\tfld %1$s\n", ((NodoExpresionBinaria) derecha).getAuxVariable());
        } else if (izquierda instanceof NodoExpresionBinaria) {
            return izquierda.generarAssembler() +
                    String.format("\tfld %1$s\n", ((NodoExpresionBinaria) izquierda).getAuxVariable()) +
                    derecha.generarAssembler();
        }
        return "";
    }
}
