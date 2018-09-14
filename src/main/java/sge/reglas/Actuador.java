package sge.reglas;

import java.util.function.Consumer;

import javax.persistence.Entity;
import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;


@Entity
public class Actuador extends SuperClase{
	
	private String nombre;
	// private Decodificador unDeco;
	
	//Ver como se hace para persistir este atributo
	private Consumer<DispositivoInteligente> funcion;

	public Actuador(String nombre, Consumer<DispositivoInteligente> unaFuncion) {
		this.nombre = nombre;
		this.funcion = unaFuncion;
	}

	public void accionar(DispositivoInteligente dispositivo) { 
//		dispositivos.forEach(unDispo->funcion.accept(unDispo));				
		funcion.accept(dispositivo);
		// unDeco.decodificarMensaje(unDispositivo.getIDfabrica()); ----> habria que
		// decodificar el mensaje para este id de fabricante que ya se lo envio por
		// parametro,
	}
}