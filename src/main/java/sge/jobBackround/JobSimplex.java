package sge.jobBackround;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sge.simplex.ProcesoSimplex;

public class JobSimplex {
	
	public void iniciar() {
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
	Runnable tarea = new Runnable () {
		public void run() {
			//ProcesoSimplex.ejecutar();
			System.out.println("Tarola");
		}
	};
	
	int delay = 3; //ejecuta cada 3 horas
	//scheduler.scheduleAtFixedRate(tarea, 0, delay, TimeUnit.HOURS);
	scheduler.scheduleAtFixedRate(tarea, 0, 5, TimeUnit.SECONDS);
	}
}
