package sge.Web;

import sge.controllers.ControllerHome;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		
		HandlebarsTemplateEngine transformes = new HandlebarsTemplateEngine();
		Spark.get("/login", ControllerHome::login, transformes);
		Spark.get("/logout", ControllerHome::logout, transformes);
		Spark.post("/principal",ControllerHome::principal, transformes);
		Spark.post("/principal/reporte",ControllerHome::reporte, transformes);
		Spark.get("/cliente/hogar/dispositivos",ControllerHome::dispositivosCliente, transformes);
		Spark.get("/cliente/hogar/ultimoperiodo",ControllerHome::ultimoperiodo, transformes);
		Spark.get("/cliente/hogar/mediciones",ControllerHome::ultimasmediciones, transformes);
		Spark.get("/cliente/hogar",ControllerHome::hogar, transformes);
		Spark.get("/cliente/optimizaciones",ControllerHome::optimizaciones, transformes);
		Spark.get("/cliente/periodos",ControllerHome::periodos, transformes);
	}
}
