package sge.reglas;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;



@Entity
public class Regla extends SuperClase{

	private String nombre;
	
	@OneToOne
	private Sensor sensorADisposicion;
	
	@OneToOne
	private Actuador actuador;
	
	@Transient //no persistimos la funci�n en s�, sino el id
	private FuncionRegla funcion;
	
	private int idFuncion;

	public Regla() {
		super();
	}
	
	public Regla(String nombre, Actuador actuador, int idFuncion) {
		this.nombre = nombre;
		this.actuador = actuador;
		this.idFuncion = idFuncion;
		this.funcion = FuncionRegla.values()[idFuncion];
	}
	
	public Regla(String nombre,Sensor sensor, Actuador actuador, int idFuncion) {
		this.nombre = nombre;
		this.sensorADisposicion = sensor;
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
			actuador.actuar(dispositivo);
		}
	}

	public int getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}
	
}
