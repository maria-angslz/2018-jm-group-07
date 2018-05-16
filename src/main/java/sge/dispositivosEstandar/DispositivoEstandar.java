package sge.dispositivosEstandar;

import sge.dispositivos.Dispositivo;

public class DispositivoEstandar extends Dispositivo {
	private int horasDeUsoDiarias;
	
	public DispositivoEstandar(String nombre, double consumoKWxH, int horas){
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxH;
		this.horasDeUsoDiarias = horas;
	}
	
	public double consumoMensual() {
		return consumoKWxHora * horasDeUsoDiarias * 30; //el 30 seria la cantidad de dias del mes
	}
	
	public void encender() {
		//lanzar excepcion;
	}
	
}
