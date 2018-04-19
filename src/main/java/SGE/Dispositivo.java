package SGE;

import java.io.FileNotFoundException;

import SGE.JSON.*;

public class Dispositivo {
	
	private String Nombre;
	private float ConsumoKWxHora;
	private Boolean Encendido;
	
	Dispositivo(String Nombre, float ConsumoKWxHora, Boolean Encendido) {
		this.Nombre = Nombre;
		this.ConsumoKWxHora = ConsumoKWxHora;
		this.Encendido = Encendido;
	}
	public static Dispositivo fromJSON() throws FileNotFoundException {
		Dispositivo disp;
		JSONObj json = JSONWrapper.FromFile("\\src\\main\\resources\\atributosDispositivos.json");
		String Nombre = json.getString("Nombre");
		float ConsumoKWxHora = (float) json.getDouble("KWxHora");
		boolean Encendido = json.getBoolean("Encendido");
		disp = new Dispositivo(Nombre, ConsumoKWxHora, Encendido);
		return disp;
	}
	
}
