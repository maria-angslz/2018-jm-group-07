package jobs;

import java.time.Period;

public class Tarea {
	Ejecutable ejecutable;
	Period periodo;

	public Tarea(Ejecutable ejecutable, Period periodo) {
		this.ejecutable = ejecutable;
		this.periodo = periodo;
	}
}
