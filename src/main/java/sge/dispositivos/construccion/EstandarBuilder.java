package sge.dispositivos.construccion;

import sge.dispositivos.estandar.DispositivoEstandar;

public class EstandarBuilder implements DispositivoBuilder<DispositivoEstandar>{
	int horas;
	
	public EstandarBuilder(int horas) {
		this.horas = horas;
	}
	
	public DispositivoEstandar create(String nombre, double kwh, double minimo, double maximo) {
		return new DispositivoEstandar(nombre, kwh, horas, maximo, minimo);
	}
}
