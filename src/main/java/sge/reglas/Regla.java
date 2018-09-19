package sge.reglas;

import java.util.function.BiFunction;
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
	
	@Transient //no persistimos la función en sí, sino el id
	private FuncionRegla funcion; //ver como persistir funciones
	
	@OneToOne
	private int idFuncion;

	public Regla() {
		super();
	}
	
	public Regla(String nombre,Sensor sensor, Actuador actuador, int idFuncion) {
		this.nombre = nombre;
		this.sensorADisposicion = sensor;
		this.actuador = actuador;
		this.idFuncion = idFuncion;
		this.funcion = FuncionRegla.values()[idFuncion];
	}
	
	public Regla(String nombre, Actuador actuador, int idFuncion) { //, Function<Float, Boolean> unaFuncion
		this.nombre = nombre;
		this.actuador = actuador;
		this.idFuncion = idFuncion;
		this.funcion = FuncionRegla.values()[idFuncion];
	}
	
	public void setFuncion(FuncionRegla funcionACumplir) {
		this.funcion = funcionACumplir;
	}
	
	public void ejecutar(DispositivoInteligente dispositivo, double maximo) { //con la medicion de un sensor
		float medicion = sensorADisposicion.medir();
		if(funcion.ejecutar(medicion,maximo)) {
			actuador.actuar(dispositivo);
		}
	}
	
	public void ejecutar(float medicion, double maximo, DispositivoInteligente dispositivo) {
		if(funcion.ejecutar(medicion,maximo)) {
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
