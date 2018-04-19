package SGE;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.JSONObject;


public class JSONWrapper {
	public static String StringFromFile(String filename) throws FileNotFoundException {
		File a = new File(filename);
		return new Scanner(a).useDelimiter("\\A").next();
	}
	
	public static JSONObj FromFile(String filename) throws FileNotFoundException {
		return new JSONObjectWrapper(filename);
	}
}

class JSONObjectWrapper implements JSONObj {
	private JSONObject JSONImpl;
	JSONObjectWrapper(String filename)  throws FileNotFoundException {
		JSONImpl = new JSONObject(JSONWrapper.StringFromFile(System.getProperty("user.dir") + filename));
	}
	JSONObjectWrapper(JSONObject rawObj) {
		JSONImpl = rawObj;
	}
	public JSONObj getObject(String key) {
		return new JSONObjectWrapper(JSONImpl.getJSONObject(key));
	}
	public String getString(String key) {
		return JSONImpl.getString(key);
	}
	public boolean getBoolean(String key) {
		return JSONImpl.getBoolean(key);
	}
	public float getFloat(String key) {
		return (float) getDouble(key);
	}
	public double getDouble(String key) {
		return JSONImpl.getDouble(key);
	}
	public String[] getKeys() {
		return JSONObject.getNames(JSONImpl);
	}
	public String toString() {
		return JSONImpl.toString();
	}
}