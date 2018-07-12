package sge.dispositivosEstandar;

import sge.dispositivos.Dispositivo;

public class DispositivoEstandar extends Dispositivo {
	private String nombre;
	private double consumoKWxHora;
	private int horasDeUsoDiarias;
	
	public DispositivoEstandar(String nombre, double consumoKWxH, int horas, double maximo, double minimo) {
		this.maximo = maximo;
		this.minimo = minimo;
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxH;
		this.horasDeUsoDiarias = horas;
	}
	public DispositivoEstandar(String nombre, double consumoKWxHora, int horas) {
		this(nombre, consumoKWxHora, horas, 0.0, 0.0);
	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public double consumoKWxHora() {
		return consumoKWxHora;
	}
	
	public double consumoMensual() {
		return consumoKWxHora * horasDeUsoDiarias * 30; //el 30 seria la cantidad de dias del mes
	}
	
}
