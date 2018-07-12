package sge.dispositivos.construccion;

import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;

public class Factory<T> {
	static Factory<DispositivoInteligente> Inteligente() {
		return new Factory<DispositivoInteligente>(new InteligenteBuilder());
	}
	static Factory<DispositivoEstandar> Estandar(int horas) {
		return new Factory<DispositivoEstandar>(new EstandarBuilder(horas));
	}
	
	DispositivoBuilder<T> builder;
	public Factory(DispositivoBuilder<T> builder) {
		this.builder = builder;
	}
	
	T AireAcondicionado(double kwh) {
		return builder.create("Aire Acondicionado", kwh, 90, 360);
	}
}
