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
	
	public T AireAcondicionado(double kwh) {
		return builder.create("Aire Acondicionado", kwh, 90, 360);
	}
	
	public T Lampara(double kwh) {
		return builder.create("Lampara", kwh, 90, 360);
	}
	
	public T Televisor(double kwh) {
		return builder.create("Televisor", kwh, 90, 360);
	}
	
	public T Lavarropas(double kwh) {
		return builder.create("Lavarropas", kwh, 6, 30);
	}
	
	public T Computadora(double kwh) {
		return builder.create("Computadora", kwh, 60, 360);
	}
	
	public T Microondas(double kwh) {
		return builder.create("Microondas", kwh, 3, 15);
	}
	
	public T Plancha(double kwh) {
		return builder.create("Plancha", kwh, 3, 30);
	}

	public T Ventilador(double kwh) {
		return builder.create("Ventilador", kwh, 120, 360);
	}
	
	
		
	
}
