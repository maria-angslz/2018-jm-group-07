package sge.dispositivos;

public class Dispositivo {
	private String nombre;
	private double consumoKWxHora;
	private boolean encendido;

	public Dispositivo(String Nombre, double d, boolean Encendido) {
		this.nombre = Nombre;
		this.consumoKWxHora = d;
		this.encendido = Encendido;
	}

	public String nombre() {
		return nombre;
	}

	public boolean encendido() {
		return encendido;
	}

	public void encendido(boolean encendido) {
		this.encendido = encendido;
	}

	public double consumoKWxHora() {
		return consumoKWxHora;
	}
}
