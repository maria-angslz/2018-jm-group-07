package sge.dispositivos.construccion;

import sge.dispositivos.TipoDeDispositivo;
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
		return builder.create("Aire Acondicionado", kwh, TipoDeDispositivo.AireAcondicionado);
	}
	
	public T Lampara(double kwh) {
		return builder.create("Lampara", kwh, TipoDeDispositivo.Lampara);
	}
	
	public T Televisor(double kwh) {
		return builder.create("Televisor", kwh, TipoDeDispositivo.Televisor);
	}
	
	public T Lavarropas(double kwh) {
		return builder.create("Lavarropas", kwh, TipoDeDispositivo.Lavarropas);
	}
	
	public T Computadora(double kwh) {
		return builder.create("Computadora", kwh, TipoDeDispositivo.Computadora);
	}
	
	public T Microondas(double kwh) {
		return builder.create("Microondas", kwh, TipoDeDispositivo.Microondas);
	}
	
	public T Plancha(double kwh) {
		return builder.create("Plancha", kwh, TipoDeDispositivo.Plancha);
	}

	public T Ventilador(double kwh) {
		return builder.create("Ventilador", kwh, TipoDeDispositivo.Ventilador);
	}
	
	
		
	
}
