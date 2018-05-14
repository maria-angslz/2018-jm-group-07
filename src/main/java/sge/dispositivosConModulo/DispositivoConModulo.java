package sge.dispositivosConModulo;
import sge.dispositivos.*;
import sge.dispositivosEstandar.*;

public class DispositivoConModulo implements Dispositivo {
	
	private String nombre;
	private double consumoKWxHora;
	private DispositivoEstandar dispositivoAsociado;
	
	public DispositivoConModulo(String nombre, double consumoKWxHora, DispositivoEstandar dispositivoAsociado) {
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxHora;
		this.dispositivoAsociado = dispositivoAsociado;
	}

	
	public double consumoKWxHora() {
		return consumoKWxHora;
	}
	
	public String nombre() {
		return nombre;
	}
	
	public double consumoDuranteUltimasHoras(int cantHoras) {
		return  dispositivoAsociado.consumoDuranteUltimasHoras(cantHoras);
	}
	
	public double consumoTotalUnPeriodo(int dias) {
		return dispositivoAsociado.consumoTotalUnPeriodo(dias);
	}

}
