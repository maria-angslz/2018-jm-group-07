package sge.Web;

import sge.controllers.ControllerHome;
import sge.controllers.ControllerCliente;
import sge.controllers.ControllerAdmin;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		
		Spark.staticFileLocation("/public");
		HandlebarsTemplateEngine transformes = new HandlebarsTemplateEngine();
		Spark.get("", ControllerHome::redirectLogin, transformes);
		Spark.get("/", ControllerHome::redirectLogin, transformes);
		Spark.get("/login", ControllerHome::login, transformes);
		Spark.get("/logout", ControllerHome::logout, transformes);
		Spark.post("/login",ControllerHome::intentarLoguear, transformes);
		
		Spark.get("/administrador/consumos",ControllerAdmin::consumos, transformes);
		Spark.get("/administrador/reporte",ControllerAdmin::reporte, transformes);
		Spark.get("/administrador/altaDispositivo",ControllerAdmin::alta, transformes);
		
		Spark.get("/cliente/hogar/dispositivos",ControllerCliente::dispositivosCliente, transformes);
		Spark.get("/cliente/hogar/ultimoperiodo",ControllerCliente::ultimoperiodo, transformes);
		Spark.get("/cliente/hogar/mediciones",ControllerCliente::ultimasmediciones, transformes);
		Spark.get("/cliente/hogar",ControllerCliente::hogar, transformes);
		Spark.get("/cliente/optimizaciones",ControllerCliente::optimizaciones, transformes);
		Spark.get("/cliente/periodos",ControllerCliente::periodos, transformes);
		
		

	}
}
