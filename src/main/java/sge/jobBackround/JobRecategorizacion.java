package sge.jobBackround;
import java.util.concurrent.*;


public class JobRecategorizacion {
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	

	Runnable task = new Runnable() {
        public void run() {
        	//tarea a automatizar       
        }
    };

    int delay = 90; //tiempo que queremos que lo haga.
    scheduler.schedule(task, delay, TimeUnit.DAYS);
    scheduler.shutdown();
    
	}
}

