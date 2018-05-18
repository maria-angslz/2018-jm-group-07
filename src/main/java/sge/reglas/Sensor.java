package sge.reglas;


public class Sensor {
	private String nombre;
	
	public Sensor(String nombre) {
		this.nombre = nombre;
	}
	
	public float medir() {
		return 0; // esta mockeado en el test.
	}
	
}
