package sge.Web;

import sge.controllers.ControllerHome;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		
		HandlebarsTemplateEngine transformes = new HandlebarsTemplateEngine();
		Spark.get("/login", ControllerHome::login, transformes);
		Spark.post("/principal",ControllerHome::principal, transformes);
		Spark.post("/principal/reporte",ControllerHome::reporte, transformes);
		Spark.get("/cliente/dispositivos",ControllerHome::reporte, transformes);
	}
}
