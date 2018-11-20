package sge.Web;

import spark.Spark;

public class Server {	
	public void iniciar() {
		Spark.port(getHerokuAssignedPort());
		//Spark.port(9000);
		Router.configure();
	}
	static int getHerokuAssignedPort() {
        //ProcessBuilder processBuilder = new ProcessBuilder();
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));

        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}