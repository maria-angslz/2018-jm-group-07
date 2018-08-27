package sge.jobBackround;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sge.simplex.ProcesoSimplex;

public class JobSimplex {
	
	public static void main(String[] args) {
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
	Runnable tarea = new Runnable () {
		public void run() {
			
			ProcesoSimplex.ejecutar(); //falta mejorar
		}
	};
	
	int delay = 3;
	scheduler.schedule(tarea, delay, TimeUnit.HOURS); //ejecuta cada 3 horas
	scheduler.shutdown();
	}
}
