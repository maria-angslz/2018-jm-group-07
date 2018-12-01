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
			ProcesoSimplex.ejecutar();
			System.out.println("JOB SIMPLEX");
		}
	};
	int delay = 5;
	scheduler.scheduleAtFixedRate(tarea, 0, delay, TimeUnit.MINUTES);
	
	}
}
