import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TablaSimbolos {
    static private BufferedWriter bw;
    static public HashMap<String, List<String>> simbolos_tabla;
    static public String RE = "\\s|\\.";

    public TablaSimbolos() {
        this.simbolos_tabla = new HashMap<>();
        try {
            File file = new File("ts.txt");
            this.bw = new BufferedWriter(new FileWriter(file));
            this.bw.write("NOMBRE,TOKEN,TIPO,VALOR,LONG");
            this.bw.newLine();
            this.bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTableCteInt(String id) throws IOException {
        writeTableCTE(id,"CTE_INT", "Integer");
    }

    public void writeTableCteFloat(String id) throws IOException {
        writeTableCTE(id,"CTE_FLOAT", "Float");
    }

    public void writeTableCteBool(String id)  throws IOException {
        writeTableCTE(id,"CTE_BOOL", "Integer");
    }

    public void writeTableCteString(String id) throws IOException {
        if (id.isEmpty()){
            writeTable("_cadena_vacia","CTE_STR","String", "", "0");
        } else {
            writeTable("_"+id.replaceAll("\"|!", ""),"CTE_STR","String", id, String.valueOf(id.length()));
        }
    }

    public void writeTableCTE(String id, String token, String type) throws IOException  {
        writeTable("_"+id, token, type, id, "\"\"");
    }

    public void writeTableID(String id, String type) throws IOException {
        writeTable(id,"ID", type, "", "\"\"");
    }

    public List<String> getElement(Object id) {
        id = ((String) id).replaceAll(RE, "_");
        return this.simbolos_tabla.get(id);
    }

    public String getType(Object id) {
        id = ((String) id).replaceAll(RE, "_");
        return this.simbolos_tabla.get(id).get(2);
    }

    public void writeTable(String id, String token, String type, String value, String length) throws IOException{
        id = id.replaceAll(RE, "_");
        if (!this.simbolos_tabla.containsKey(id)) {
            String s = String.join(",", Arrays.asList(id, token, type, value, length));
            bw.write(s.replaceAll(" ", "_"));
            bw.newLine();
            bw.flush();
            simbolos_tabla.put(id,Arrays.asList(token, value, type));
        } else {
            if (!token.equals(simbolos_tabla.get(id).get(0)))
                throw new IOException("[!ERROR] ID: ["+id+"] ya se encuentra definido como "+simbolos_tabla.get(id) + ", NO es posible definirlo como " + token);
        }
    }
}
