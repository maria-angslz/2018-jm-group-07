package sge;

import javax.persistence.Entity;

@Entity
public class Coordenates {
	double latitud;
	double longitud;
	
	public Coordenates(double longitud, double latitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public double X() {
		return longitud;
	}
	
	public double Y() {
		return latitud;
	}
	
	public double distancia(Coordenates coordenada) {
		return Math.sqrt( Math.pow(this.X() - coordenada.X(), 2)  + Math.pow(this.Y() - coordenada.Y(),2));
	}
	
	
}
