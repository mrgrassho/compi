package ast;

import java.util.Arrays;

public class NodoIfUnario extends NodoIfElse implements NodoAsignacionInterface {

    public NodoIfUnario(NodoIdentificador identificador, NodoCondicion condicion, NodoExpresion bodyIf, NodoExpresion bodyIfElse) {
        super(
            "IF_UNARIO",
            condicion,
            new NodoBloqueSentencias(Arrays.asList(new NodoAsignacion(identificador, bodyIf))),
            new NodoBloqueSentencias(Arrays.asList(new NodoAsignacion(identificador, bodyIfElse)))
        );
    }
}

