package sge.reglas;

import java.util.function.Consumer;

import sge.dispositivos.inteligentes.*;

public enum Funcion {

	APAGAR(disp -> disp.apagar()), // valor 0
	ENCENDER(disp -> disp.encender()); // valor 1

	Consumer<DispositivoInteligente> accion;

	Funcion(Consumer<DispositivoInteligente> unaAccion) {
		this.accion = unaAccion;
	}

	public void accionar(DispositivoInteligente dispositivo) {
		accion.accept(dispositivo);
	}

}
