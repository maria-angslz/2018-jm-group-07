package sge.dispositivos.construccion;

import sge.dispositivos.inteligentes.DispositivoInteligente;

public class InteligenteBuilder implements DispositivoBuilder<DispositivoInteligente>{
	public InteligenteBuilder() {
		
	}
	
	public DispositivoInteligente create(String nombre, double kwh, double maximo, double minimo) {
		return new DispositivoInteligente(nombre, kwh, maximo, minimo);
	}
}
