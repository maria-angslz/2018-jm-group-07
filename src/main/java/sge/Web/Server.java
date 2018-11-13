package sge.Web;

import spark.Spark;

public class Server {	
	public void iniciar() {
		Spark.port(9000);
		Router.configure();
	}
}