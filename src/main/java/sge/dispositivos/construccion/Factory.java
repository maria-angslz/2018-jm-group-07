package sge.dispositivos.construccion;

import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;

public class Factory<T> {
	public static Factory<DispositivoInteligente> Inteligente() {
		return new Factory<DispositivoInteligente>(new InteligenteBuilder());
	}
	public static Factory<DispositivoEstandar> Estandar(int horas) {
		return new Factory<DispositivoEstandar>(new EstandarBuilder(horas));
	}
	
	DispositivoBuilder<T> builder;
	public Factory(DispositivoBuilder<T> builder) {
		this.builder = builder;
	}
	
	public T AireAcondicionado(double kwh) {
		return builder.create("Aire Acondicionado", kwh, 360, 90);
	}
}
