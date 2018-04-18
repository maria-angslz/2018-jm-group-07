package SGE;
import java.io.InputStream;
import java.util.Scanner;
import org.json.JSONObject;

public class JSONUtils {
	public static String getJsonStringFromFile(String path) {
		Scanner scanner;
		InputStream in = FileHandler.inputStreamFromFile(path);
		scanner = new Scanner(in);
		String json = scanner.useDelimiter("\\Z").next();
		scanner.close();
		return json;
	}
	
	public static JSONObject getJsonObjectFromFile(String path) {
		return new JSONObject(getJsonStringFromFile(path));
	}
}
