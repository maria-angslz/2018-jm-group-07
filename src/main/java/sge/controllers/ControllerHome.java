package sge.controllers;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerHome {
	public static ModelAndView index(Request req, Response res) {
		/*Usuario user = UsuarioRepositorio.get().findAny();
 		String apodo = req.queryParams("apodo");
		List<Captura> capturas = 
				Optional.fromNullable(apodo)
				.transform(it -> user.findByApodo(it))
				.or(user.getCapturas());*/
		String email = req.queryParams("go");
		HashMap<String, Object> viewModel = new HashMap<>();
		//viewModel.put("apodo", apodo);
		//viewModel.put("capturas", capturas);
		
		return new ModelAndView(
				viewModel, 
				"login.hbs");
	}
	
	public static ModelAndView principal(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(
				viewModel, 
				"ViewReporte.html");
	}
}
