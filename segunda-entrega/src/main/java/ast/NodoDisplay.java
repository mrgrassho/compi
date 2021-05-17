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
}
