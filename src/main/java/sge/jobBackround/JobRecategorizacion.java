package sge.jobBackround;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sge.persistencia.repos.RepoClientes;

public class JobRecategorizacion {
	public void iniciar() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable task = new Runnable() {
			public void run() {
				RepoClientes.getInstance().get().stream().forEach(unCliente -> unCliente.recategorizar());
				System.out.println("JOB RECATEGORIZACION");
			}
		};

		//int delay = 90; // tiempo que queremos que lo haga (3 meses = 90 dias).
		int delay = 2; 
		scheduler.scheduleAtFixedRate(task, 0, delay, TimeUnit.MINUTES);
		
	}
}
