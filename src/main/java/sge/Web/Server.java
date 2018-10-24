package sge.Web;


import java.io.FileNotFoundException;

import sge.persistencia.json.CargaDatosWrapper;
import spark.Spark;

public class Server {
	public static CargaDatosWrapper cargador = new CargaDatosWrapper();
	
	public static void main(String[] args) throws FileNotFoundException {
		//Bootstrap.init();
		cargador.cargarClientes();
		cargador.cargarAdmins();
		Spark.port(9000);
		Router.configure();
	}
}