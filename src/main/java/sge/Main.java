package sge;

import sge.persistencia.json.CargaDatosWrapper;
import sge.Web.Server;
import sge.jobBackround.*;

public class Main {
	public static CargaDatosWrapper cargador = new CargaDatosWrapper();
	
	public static void main(String[] args) {
		cargador.cargarAdmins();
		cargador.cargarCategorias();
		cargador.cargarClientes();
		cargador.cargarZona();
		
		JobRecategorizacion recategorizacion = new JobRecategorizacion();
		JobSimplex simplex = new JobSimplex();
		recategorizacion.iniciar();
		simplex.iniciar();
		
		//Cargo server
		Server servidor = new Server();
		servidor.iniciar();
	}
}
