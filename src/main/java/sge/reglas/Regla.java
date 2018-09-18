package sge.reglas;

import java.util.function.Function;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;

//import java.util.List;

@Entity
public class Regla extends SuperClase{

	private String nombre;
	//private List<Sensor> sensoresADisposicion; //pensamos que seria en un futuro una lista de sensores y actuadores, pero por el momenton por falta de informacion al respecto, lo planteamos para uno solo.
	//private List<Actuador> actuadores;
	
	@OneToOne
	private Sensor sensorADisposicion;
	
	@OneToOne
	private Actuador actuador;
	
	@Transient //es momentaneo, se deberia solucionar con respecto a lo hablado
	private Function<Float,Boolean> funcion; //ver como persistir funciones
	
	private int idFuncion;

	public Regla() {
		super();
	}
	
	public Regla(String nombre,Sensor sensor, Actuador actuador) {
		this.nombre = nombre;
		this.sensorADisposicion = sensor;
		this.actuador = actuador;
	}
	
	public Regla(String nombre, Actuador actuador) { //, Function<Float, Boolean> unaFuncion
		this.nombre = nombre;
		this.actuador = actuador;
		//this.funcion = unaFuncion;
	}
	
	public void setFuncion(Function<Float,Boolean> funcionACumplir) {
		this.funcion = funcionACumplir;
	}
	
	public void ejecutar(DispositivoInteligente dispositivo) { //con la medicion de un sensor
		float medicion = sensorADisposicion.medir();
		if(funcion.apply(medicion)) {
			actuador.actuar(dispositivo);
		}
		
	}
	
	public void ejecutar(float medicion, DispositivoInteligente dispositivo) {
		if(funcion.apply(medicion)) {
			actuador.actuar(dispositivo); //recibir la lista de dispositivos y pasarla como parametro
		}
	}

	public int getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}
	
}
