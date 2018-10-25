package sge.controllers;

import sge.Web.logger;
import sge.clientes.Cliente;
import sge.persistencia.json.CargaDatosWrapper;
import sge.persistencia.repos.RepoClientes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.nio.charset.Charset;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class ControllerHome {
	public static boolean lanzarCartel = false;
	public static CargaDatosWrapper cargador = new CargaDatosWrapper();
	public static RepoClientes repoClientes = RepoClientes.getInstance();

	public static ModelAndView login(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();
		if (lanzarCartel) {
			viewModel.put("booleano", true);
			lanzarCartel = false;
		} else {
			viewModel.remove("booleano");
		}

		return new ModelAndView(viewModel, "login.hbs");
	}

	public static ModelAndView logout(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		req.session().invalidate();
		res.redirect("/login");
		return new ModelAndView(viewModel, "");
	}

	public static ModelAndView principal(Request req, Response res) {

		List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

		Map<String, String> params = toMap(pairs);

		String email = params.get("email");
		int password = Integer.parseInt(params.get("password"));

		logger session = new logger();
		HashMap<String, Object> viewModel = new HashMap<>();

		if (session.checkPass(email, password, "admin")) {

			viewModel.put("clientes", repoClientes.get());

			return new ModelAndView(viewModel, "ViewConsumos.hbs");

		} else if (session.checkPass(email, password, "cliente")) {
			Optional<Cliente> cliente = repoClientes.get().stream()
					.filter(c -> c.getEmail().equals(email) && c.pass().equals(password)).findFirst();

			if (cliente.isPresent()) {
				req.session(true);
				req.session().attribute("cliente", cliente.get());
				res.redirect("/cliente/hogar");
			} else {
				res.redirect("/login");
			}
			return new ModelAndView(viewModel, "");
		} else {
			lanzarCartel = true;
			res.redirect("/login");

			return new ModelAndView(viewModel, "login.hbs");

		}

	}

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

	public static ModelAndView ultimasmediciones(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "ViewMediciones.hbs");
	}

	public static ModelAndView optimizaciones(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);

		return new ModelAndView(viewModel, "ViewOptimizaciones.hbs");
	}

	public static ModelAndView ultimoperiodo(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "ViewConsumoUltimoPeriodo.hbs");
	}

	public static ModelAndView hogar(Request req, Response res) {
		res.redirect("/cliente/hogar/mediciones");
		HashMap<String, Object> viewModel = new HashMap<>();
		autenticarCliente(req, res, viewModel);
		return new ModelAndView(viewModel, "");
	}

	public static ModelAndView periodos(Request req, Response res) {
		HashMap<String, Object> viewModel = new HashMap<>();
		return new ModelAndView(viewModel, "ViewPeriodos.hbs");
	}

	public static ModelAndView reporte(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();

		return new ModelAndView(viewModel, "login.hbs");
	}

	private static Map<String, String> toMap(List<NameValuePair> pairs) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < pairs.size(); i++) {
			NameValuePair pair = pairs.get(i);
			map.put(pair.getName(), pair.getValue());
		}
		return map;
	}

}
