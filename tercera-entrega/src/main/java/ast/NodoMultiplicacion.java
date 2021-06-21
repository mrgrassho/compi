package ast;

public class NodoMultiplicacion extends NodoExpresionBinaria {

    public NodoMultiplicacion(NodoExpresion izquierda, NodoExpresion derecha) {
        super("*", izquierda, derecha);
    }

    @Override
	public String generarAssembler() {
		return  super.generarAssembler() +
                String.format("\tfmul\n") +
                String.format("\tfstp %1$s\n", this.getAuxVariable());
	}
}
