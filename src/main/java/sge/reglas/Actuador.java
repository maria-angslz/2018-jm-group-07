package sge.reglas;

import javax.persistence.Entity;
import javax.persistence.Transient;

import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;


@Entity
public class Actuador extends SuperClase{

	private String nombre;
	
	@Transient //no persistimos la funci�n en s�, sino el id
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
	
	
	public Actuador(String unNombre, int unIdFuncion) {
		this.nombre = unNombre;
		this.idFuncion = unIdFuncion;
		this.funcion = Funcion.values()[unIdFuncion];
	}

	public void actuar(DispositivoInteligente dispositivo) { 
				
		funcion.accionar(dispositivo);
	}
}