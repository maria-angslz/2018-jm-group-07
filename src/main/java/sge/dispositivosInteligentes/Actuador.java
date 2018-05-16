package sge.dispositivosInteligentes;

import java.util.function.Consumer;

public class Actuador {
	private String nombre;
	//private Decodificador unDeco;
	private Consumer<DispositivoInteligente> funcion;
	
	public Actuador(String nombre, /*,Decodificador unDeco,*/ Consumer<DispositivoInteligente> unaFuncion) {
		this.nombre = nombre;
		//this.unDeco = unDeco;
		this.funcion = unaFuncion;
	}
	
	public void accionar(DispositivoInteligente unDispositivo) {
		funcion.accept(unDispositivo);
		//unDeco.decodificarMensaje(unDispositivo.getIDfabrica()); ----> habria que decodificar el mensaje para este id de fabricante que ya se lo envio por parametro,
		
	}
	
}
