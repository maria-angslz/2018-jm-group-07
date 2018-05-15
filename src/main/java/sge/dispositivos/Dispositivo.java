package sge.dispositivos;

public abstract class Dispositivo {
	public String nombre;
	public double consumoKWxHora;
	
	public String nombre() {
		return this.nombre;
	}
	
	public double consumoKWxHora() {
		return consumoKWxHora;
	}
	
	public abstract double consumoMensual();
	
	public abstract void encender();
	
	public int puntosPorAgregarDisp() {
		return 0;
	}
	
	public boolean encendido() {
		return false;
	}
	
	public boolean apagado() {
		return false;
	}

}
