package sge.reglas;

import java.util.List;
import java.util.function.Consumer;

import sge.dispositivos.inteligentes.DispositivoInteligente;

public class Actuador {
	private String nombre;
	//private Decodificador unDeco;
	private List<DispositivoInteligente> dispositivos;
	private DispositivoInteligente dispositivo;
	private Consumer<DispositivoInteligente> funcion;
	
	public Actuador(String nombre, List<DispositivoInteligente> dispositivos, Consumer<DispositivoInteligente> unaFuncion  /*,Decodificador unDeco,*/) {
		this.nombre = nombre;
		//this.unDeco = unDeco;
		this.funcion = unaFuncion;
		this.dispositivos = dispositivos;
	}
	
	public Actuador(String nombre, DispositivoInteligente dispositivo, Consumer<DispositivoInteligente> unaFuncion) {
		this.nombre = nombre;
		this.funcion = unaFuncion;
		this.dispositivo = dispositivo;
	}
	
	public void accionar() {
		if(dispositivo != null) {
			funcion.accept(dispositivo);
		}else {
			dispositivos.forEach(unDispo->funcion.accept(unDispo));
		}
		
		//unDeco.decodificarMensaje(unDispositivo.getIDfabrica()); ----> habria que decodificar el mensaje para este id de fabricante que ya se lo envio por parametro,
		
	}
	
}
