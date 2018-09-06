package sge.dispositivos.construccion;

import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.dispositivos.inteligentes.TipoDeDispositivo;

public class InteligenteBuilder implements DispositivoBuilder<DispositivoInteligente>{
	public InteligenteBuilder() {
		
	}
	
	public DispositivoInteligente create(String nombre, double kwh, TipoDeDispositivo tipo) { //double minimo, double maximo
		return new DispositivoInteligente(nombre, kwh, tipo); //maximo, minimo
	}
}
