package sge.reglas;

import javax.persistence.Entity;
import sge.SuperClase;

@Entity
public abstract class Sensor extends SuperClase{
	
	public float medir() {
		return -1; //valor negativo para reflejar que no hay implementación correcta aún
	};
	
}