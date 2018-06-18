package sge;

import java.util.HashMap;

public class Coordenates {
	HashMap<String,Double> coordenada = new HashMap<String,Double>();
	
	public Coordenates(double longitud, double latitud) {
		coordenada.put("latitud", latitud);
		coordenada.put("longitud", longitud);
	}
	public HashMap<String, Double> coordenadas(){
		return coordenada;
	}
	public double X() {
		return coordenada.get("longitud");
	}
	
	public double Y() {
		return coordenada.get("latitud");
	}
	
}
