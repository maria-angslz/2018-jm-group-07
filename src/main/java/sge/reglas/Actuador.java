package sge.reglas;

import java.util.function.Consumer;
import sge.dispositivosInteligentes.DispositivoInteligente;

public class Actuador {
	private String nombre;
	//private Decodificador unDeco;
	private DispositivoInteligente dispositivo;
	private Consumer<DispositivoInteligente> funcion;
	
	public Actuador(String nombre, DispositivoInteligente dispositivo, Consumer<DispositivoInteligente> unaFuncion  /*,Decodificador unDeco,*/) {
		this.nombre = nombre;
		//this.unDeco = unDeco;
		this.funcion = unaFuncion;
		this.dispositivo = dispositivo;
	}
	
	public void accionar() {
		funcion.accept(dispositivo);
		//unDeco.decodificarMensaje(unDispositivo.getIDfabrica()); ----> habria que decodificar el mensaje para este id de fabricante que ya se lo envio por parametro,
		
	}
	
}
