package ast;

public class NodoResta extends NodoExpresionBinaria {

    public NodoResta(NodoExpresion izquierda, NodoExpresion derecha) {
        super("-", izquierda, derecha);
    }

    @Override
	public String generarAssembler() {
		return  super.generarAssembler() +
                String.format("\tfsub\n") +
                String.format("\tfstp %1$s\n", this.getAuxVariable());
	}
}