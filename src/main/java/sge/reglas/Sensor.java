package sge.reglas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Sensor {
	
	@Id @GeneratedValue
	long id;
	
	public float medir() {
		return -1; //valor negativo para reflejar que no hay implementación correcta aún
	};
	
}