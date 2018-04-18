package SGE;
import java.io.InputStream;

public class FileHandler {
	public static InputStream inputStreamFromFile(String path) {
		InputStream archivoJ = FileHandler.class.getResourceAsStream(path);
		return archivoJ;
	}
	
}
