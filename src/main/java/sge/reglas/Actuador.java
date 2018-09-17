package sge.reglas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;


@Entity
public class Actuador extends SuperClase{

	private String nombre;
	// private Decodificador unDeco;
	
	@Transient //es momentaneo! se deberia realizar el cambio hablado 
	//Ver como se hace para persistir este atributo
	private Funcion funcion; //le cambiamos el tipo

	private int idFuncion;
	
	public int getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}

	public Actuador() {
		super();
	}
	
//	public Actuador(String nombre, Consumer<DispositivoInteligente> unaFuncion) {
//		this.nombre = nombre;
//		this.funcion = unaFuncion;
//	}
//
	
	public Actuador(String unNombre, int unIdFuncion) {
		this.nombre = unNombre;
		this.idFuncion = unIdFuncion;
		this.funcion = Funcion.values()[unIdFuncion];
	}

	public void actuar(DispositivoInteligente dispositivo) { 
				
		funcion.accionar(dispositivo);
		// unDeco.decodificarMensaje(unDispositivo.getIDfabrica()); ----> habria que
		// decodificar el mensaje para este id de fabricante que ya se lo envio por
		// parametro,
	}
}