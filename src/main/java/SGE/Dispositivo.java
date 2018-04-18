package SGE;
import org.json.JSONObject;


public class Dispositivo {
	
	static String NombreGenerico;
	static float ConsumoKWxHora;
	static Boolean EstaEncendido;
	
	public static void main(String[] args) {
		JSONObject obj = JSONUtils.getJsonObjectFromFile("/atributosDispositivos.json");
		String[] names = JSONObject.getNames(obj);
		NombreGenerico = obj.get(names[0]).toString();
		ConsumoKWxHora = obj.getFloat(names[1]);
		EstaEncendido = obj.getBoolean(names[2]);
	}
	
}
