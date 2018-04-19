package SGE.Dispositivos;

import java.io.FileNotFoundException;

import SGE.JSON.*;

public class Dispositivo {
	
	private String nombre;
	private float consumoKWxHora;
	private boolean encendido;
	
	Dispositivo(String Nombre, float ConsumoKWxHora, boolean Encendido) {
		this.nombre = Nombre;
		this.consumoKWxHora = ConsumoKWxHora;
		this.encendido = Encendido;
	}
	public static Dispositivo fromJSONFile(String filename) throws FileNotFoundException {
		JSONObj json = JSONWrapper.FromFile(filename);
		return fromJSONObject(json);
	}
	public static Dispositivo fromJSONFile() throws FileNotFoundException {
		return fromJSONFile("\\src\\main\\resources\\atributosDispositivos.json");
	}
	public static Dispositivo fromJSONObject(JSONObj json) {
		Dispositivo disp;
		String Nombre = json.getString("Nombre");
		float ConsumoKWxHora = (float) json.getDouble("KWxHora");
		boolean Encendido = json.getBoolean("Encendido");
		disp = new Dispositivo(Nombre, ConsumoKWxHora, Encendido);
		return disp;
	}
	
}
