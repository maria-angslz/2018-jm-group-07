package sge.jobBackround;

import java.util.concurrent.*;

public class JobRecategorizacion {
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		Runnable task = new Runnable() {
			public void run() {
				// tarea a automatizar
				// Deberia ser un foreach para la lista de clientes del repoClientes,
				// enviandoles el msje recategorizacion
			}
		};

		int delay = 90; // tiempo que queremos que lo haga (3 meses = 90 dias).
		scheduler.schedule(task, delay, TimeUnit.DAYS);
		scheduler.shutdown();

	}
}
