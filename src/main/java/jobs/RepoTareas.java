package jobs;

import java.time.Period;
import java.util.List;

public class RepoTareas {
	List<Tarea> tareas;

	static RepoTareas instancia;
	
	private RepoTareas() { }
	
	public static RepoTareas getInstance() {
		if (instancia == null)
			instancia = new RepoTareas();
		return instancia;
	}
	
	public List<Tarea> all() {
		return tareas;
	}
	
	public void agregrar(Tarea tarea) {
		tareas.add(tarea);
	}

	public void agregrar(Ejecutable ejecutable, Period periodo) {
		tareas.add(new Tarea(ejecutable, periodo));
	}
}
