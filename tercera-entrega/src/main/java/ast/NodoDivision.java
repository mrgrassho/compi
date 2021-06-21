package ast;

public class NodoDivision extends NodoExpresionBinaria {

    public NodoDivision(NodoExpresion izquierda, NodoExpresion derecha) {
        super("/", izquierda, derecha);
    }

    @Override
	public String generarAssembler() {
		return  super.generarAssembler() +
                String.format("\tfdiv\n") +
                String.format("\tfstp %1$s\n", this.getAuxVariable());
	}
}
