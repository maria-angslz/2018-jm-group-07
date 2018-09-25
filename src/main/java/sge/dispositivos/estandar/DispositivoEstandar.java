package sge.dispositivos.estandar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import sge.dispositivos.Dispositivo;
import sge.dispositivos.TipoDeDispositivo;

@Entity
public class DispositivoEstandar extends Dispositivo {
	
	private int horasDeUsoDiarias;
	
	public DispositivoEstandar(String unNombre, double consumoKWxH, int horas, TipoDeDispositivo tipo) { //double maximo, double minimo
//		this.maximo = maximo;
//		this.minimo = minimo;
		this.tipo = tipo;
		nombre = unNombre;
		this.consumoKWxHora = consumoKWxH;
		this.horasDeUsoDiarias = horas;
	}
//	public DispositivoEstandar(String nombre, double consumoKWxHora, int horas) {
//		this(nombre, consumoKWxHora, horas, 0.0, 0.0);
//	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public double consumoKWxHora() {
		return consumoKWxHora;
	}
	
	public double consumoMensual() {
		return consumoKWxHora * horasDeUsoDiarias * 30; //el 30 seria la cantidad de dias del mes
	}
	
	public TipoDeDispositivo getTipo() {
		return tipo;
	}

	@Override
	public double consumoPromedioPorPeriodo(int unPeriodo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
