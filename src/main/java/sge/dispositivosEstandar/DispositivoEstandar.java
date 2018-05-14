package sge.dispositivosEstandar;

import sge.dispositivos.Dispositivo;

public class DispositivoEstandar implements Dispositivo {
	private String nombre;
	private double consumoKWxHora;
	private int horasDeUsoDiarias;
	
	public DispositivoEstandar(String nombre, double consumoKWxH, int horas){
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxH;
		this.horasDeUsoDiarias = horas;
	}

	public String nombre() {
		return nombre;
	}

	public double consumoKWxHora() {
		return consumoKWxHora;
	}

	public double consumoDuranteUltimasHoras(int cantHoras) {
		return consumoKWxHora * cantHoras;
	}

	public double consumoTotalUnPeriodo(int cantDias) {

		return this.consumoKWxHora() * horasDeUsoDiarias * cantDias;
	}

}
