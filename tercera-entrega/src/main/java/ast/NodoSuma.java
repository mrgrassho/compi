package ast;

public class NodoSuma extends NodoExpresionBinaria {

    public NodoSuma(NodoExpresion izquierda, NodoExpresion derecha) {
        super("+", izquierda, derecha);
    }
    
    @Override
	public String generarAssembler() {
		return  super.generarAssembler() +
                String.format("\tfadd\n") +
                String.format("\tfstp %1$s\n", this.getAuxVariable());
	}
}