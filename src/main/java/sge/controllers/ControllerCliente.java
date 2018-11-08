package sge.controllers;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerCliente {
	
	public static void autenticarCliente(Request req, Response res, HashMap<String, Object> viewModel) {
		Object cliente = req.session().attribute("cliente");
		if (cliente == null)
			res.redirect("/login");
		viewModel.put("cliente", cliente);
	}
	
	public static ModelAndView dispositivosCliente(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "ViewEstadoDispositivos.hbs");
	}
	
	public static ModelAndView ultimoperiodo(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "ViewConsumoUltimoPeriodo.hbs");
	}
	
	public static ModelAndView ultimasmediciones(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "ViewMediciones.hbs");
	}
	
	public static ModelAndView hogar(Request req, Response res) {
		res.redirect("/cliente/hogar/mediciones");
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "");
	}
	
	public static ModelAndView optimizaciones(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);

		return new ModelAndView(viewModel, "ViewOptimizaciones.hbs");
	}
	
	public static ModelAndView periodos(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "ViewPeriodos.hbs");
	}

}
