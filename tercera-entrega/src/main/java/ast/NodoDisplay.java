package ast;

import java.util.List;

public class NodoDisplay extends NodoSentencia {
    private final String mensaje;

    public NodoDisplay(String mensaje) {
        super("DISPLAY");
        this.mensaje = mensaje.replaceAll("\"", "");
    }

    @Override
    public String getDescripcionNodo() {
        return "DISPLAY: " + this.mensaje;
    }

    public String getSalida() {
        return "_" + this.mensaje.replace(" ", "_");
    }
	
	@Override
	public String generarAssembler() {
		StringBuilder lineas = new StringBuilder();
		lineas.append(String.format("\tmov dx, OFFSET %1$s\n", getSalida()));
		lineas.append("\tmov ah, 9\n");
		lineas.append("\tint 21h\n");
		lineas.append("\tmov ah, 2\n");
		lineas.append("\tmov dl, 13\n");
		lineas.append("\tint 21h\n");
		lineas.append("\tmov dl, 10\n");
		lineas.append("\tint 21h\n");
		return lineas.toString();
	}
}
