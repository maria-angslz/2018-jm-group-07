package sge.dispositivos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Dispositivo {
	private String nombre;
	private double consumoKWxHora;
	private boolean encendido;
	private double consumoDeEsteMes;
	private LocalDateTime fechaDeEncendido;

	public Dispositivo(String Nombre, double d, boolean Encendido) {
		this.nombre = Nombre;
		this.consumoKWxHora = d;
		this.encendido = Encendido;
		if(encendido)
			fechaDeEncendido = LocalDateTime.now();
	}

	public void setConsumoDeEsteMes(double consumoDeEsteMes) {
		this.consumoDeEsteMes = consumoDeEsteMes;
	}

	public String nombre() {
		return nombre;
	}

	public boolean encendido() {
		return encendido;
	}

	public void encendido(boolean encendido) {
		actualizarConsumo();
		this.encendido = encendido;
		if (encendido)
			fechaDeEncendido = LocalDateTime.now();
	}

	public double consumoKWxHora() {
		return consumoKWxHora;
	}

	private void actualizarConsumo() {
		if (encendido) {
			LocalDateTime now = LocalDateTime.now();
			consumoDeEsteMes += ChronoUnit.MINUTES.between(fechaDeEncendido, now) / 60 * consumoKWxHora;
			fechaDeEncendido = now;
		}
	}

	public double consumoDeEsteMes() {
		return consumoDeEsteMes;
	}

	public void finDeMes() {
		consumoDeEsteMes = 0;
	}
}
