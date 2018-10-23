package sge.Web;

import sge.controllers.ControllerHome;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	public static void configure() {
		
		HandlebarsTemplateEngine transformes = new HandlebarsTemplateEngine();
		Spark.get("/", ControllerHome::index, transformes);	
	}
}
