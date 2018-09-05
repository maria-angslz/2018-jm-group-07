package sge.reglas;

import java.util.List;
import java.util.function.Consumer;

import sge.dispositivos.inteligentes.DispositivoInteligente;

public class Actuador {
	private String nombre;
	// private Decodificador unDeco;
	private Consumer<DispositivoInteligente> funcion;

	public Actuador(String nombre, Consumer<DispositivoInteligente> unaFuncion) {
		this.nombre = nombre;
		this.funcion = unaFuncion;
	}

	public void accionar(DispositivoInteligente dispositivo) { 
//			dispositivos.forEach(unDispo->funcion.accept(unDispo));				
		funcion.accept(dispositivo);
		// unDeco.decodificarMensaje(unDispositivo.getIDfabrica()); ----> habria que
		// decodificar el mensaje para este id de fabricante que ya se lo envio por
		// parametro,

	}

}