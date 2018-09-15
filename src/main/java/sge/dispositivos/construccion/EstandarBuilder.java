package sge.dispositivos.construccion;

import sge.dispositivos.TipoDeDispositivo;
import sge.dispositivos.estandar.DispositivoEstandar;

public class EstandarBuilder implements DispositivoBuilder<DispositivoEstandar>{
	int horas;
	
	public EstandarBuilder(int horas) {
		this.horas = horas;
	}
	
	public DispositivoEstandar create(String nombre, double kwh, TipoDeDispositivo tipo) {
		return new DispositivoEstandar(nombre, kwh, horas, tipo);
	}
}
