package ast;

public class NodoCondicionSimple extends NodoCondicion{
    private final NodoComparacion comparacion;
    private final String nombre;

    public NodoCondicionSimple(String nombre, NodoComparacion comparacion) {
        super(nombre);
        this.nombre = nombre;
        this.comparacion = comparacion;
        if (nombre != null)
            if (nombre.equals("NOT"))
                this.comparacion.setOperador(this.comparacion.getOperadorInverso());
    }

    @Override
    protected String graficar(String idPadre) {
        final String miId = this.getIdNodo();
        if (this.nombre != null) {
            return super.graficar(idPadre) +
                    comparacion.graficar(miId);
        } else {
            return comparacion.graficar(idPadre);
        }
    }

    @Override
    protected String generarAssembler(String endTag) {
        return comparacion.generarAssembler(endTag);
    }
}
