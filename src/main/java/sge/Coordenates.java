package sge;

import javax.persistence.Embeddable;

@Embeddable
public class Coordenates {

	float latitud;
	float longitud;
	
	public Coordenates() {
		super();
	}
	
	public Coordenates(float longitud, float latitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public float X() {
		return longitud;
	}
	
	public float Y() {
		return latitud;
	}
	
	public double distancia(Coordenates coordenada) {
		return Math.sqrt( Math.pow(this.X() - coordenada.X(), 2)  + Math.pow(this.Y() - coordenada.Y(),2));
	}
	
	
}
