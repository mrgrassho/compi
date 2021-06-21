
import ast.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws Exception {
		if (args.length != 1) throw new Exception("Usage: $java Main filaname");
    parser sintactico= new parser (new Lexer(new FileReader(args[0])));
    NodoPrograma programa = (NodoPrograma) sintactico.parse().value;

    try {
      FileWriter archivo = new FileWriter("arbol.dot");
      PrintWriter pw = new PrintWriter(archivo);
      pw.println(programa.graficar());
      archivo.close();
    } catch (Exception e) {
      System.out.println(e);
    }

    //String cmd = "dot -Tpng arbol.dot -o arbol.png";
    //Runtime.getRuntime().exec(cmd);

    try {
      FileWriter archivo = new FileWriter("data.asm");
      PrintWriter pw = new PrintWriter(archivo);
      pw.println(programa.generarAssembler());
      archivo.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
