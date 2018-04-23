package sge.dispositivos;


public class Dispositivo {
	private String nombre;
	private double consumoKWxHora;
	private boolean encendido;
	private double consumoDeEsteMes;
	
	public Dispositivo(String Nombre, double d, boolean Encendido) {
		this.nombre = Nombre;
		this.consumoKWxHora = d;
		this.encendido = Encendido;
	}
	public String nombre() { return nombre; }
	public boolean encendido() { return encendido; }
	public double consumoKWxHora() {	return consumoKWxHora;	}
	public double consumoDeEsteMes() { return consumoDeEsteMes; }
	public void pasoUnaHora() {
		if(encendido)
			consumoDeEsteMes += consumoKWxHora;
	}
	public void finDeMes() {
		consumoDeEsteMes = 0;
	}
}
