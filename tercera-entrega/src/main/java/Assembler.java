import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Assembler {
	public static final String FILENAME = "final.asm";

	public static void escribirAssembler(List<String> lines) {
		try (PrintWriter out = new PrintWriter(new FileWriter(FILENAME, true))) {
			for (String line: lines) {
				out.println(line);
			}
			out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
