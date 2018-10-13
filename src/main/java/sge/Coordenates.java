package sge;

import javax.persistence.Embeddable;

@Embeddable
public class Coordenates {

	Double latitud;
	Double longitud;
	
	public Coordenates() {
		super();
	}
	
	public Coordenates(Double longitud, Double latitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public Double X() {
		return longitud;
	}
	
	public Double Y() {
		return latitud;
	}
	
	public double distancia(Coordenates coordenada) {
		return Math.sqrt( Math.pow(this.X() - coordenada.X(), 2)  + Math.pow(this.Y() - coordenada.Y(),2));
	}
	
	
}
