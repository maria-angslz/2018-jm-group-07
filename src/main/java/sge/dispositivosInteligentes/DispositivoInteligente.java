package sge.dispositivosInteligentes;

import sge.dispositivos.*;

class dispositivoInteligente implements Dispositivo {
	private String nombre;
	private double consumoKWxHora;
	private boolean encendido; // cambiar antes de la entrega, se incorpora el modo "ahorro de energia"
	private EstadoDispositivo estado;

	public dispositivoInteligente(String nombre, double consumoKWxHora, boolean encendido) {
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxHora;
		this.encendido = encendido;
	}

	public String nombre() {
		return this.nombre;
	}

	public double consumoKWxHora() {
		return consumoKWxHora;
	}

	public double consumoDuranteUltimasHoras(int cantHoras) {
		return consumoKWxHora * cantHoras;
	}

	public double consumoTotalUnPeriodo(int dias) {
		return consumoKWxHora * 24 * dias; // el 24 refiere a las 24 hs de un dia
	}

	public boolean encendido() {
		return estado.devolverEstado();
	}

	public boolean apagado() {
		return !encendido;
	}

	public void encendido(boolean encendido) {
		this.encendido = encendido;
	}

	public void apagar() {
		this.encendido(false);
	}

	public void encender() {
		this.encendido(true);
	}
	
	public int puntosPorAgregarDisp() {
		return 15;
	}
}
