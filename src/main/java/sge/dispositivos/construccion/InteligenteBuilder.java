package sge.dispositivos.construccion;

import sge.dispositivos.TipoDeDispositivo;
import sge.dispositivos.inteligentes.DispositivoInteligente;

public class InteligenteBuilder implements DispositivoBuilder<DispositivoInteligente>{
	public InteligenteBuilder() {
		
	}
	
	public DispositivoInteligente create(String nombre, double kwh, TipoDeDispositivo tipo) { //double minimo, double maximo
		return new DispositivoInteligente(nombre, kwh, tipo); //maximo, minimo
	}
}
