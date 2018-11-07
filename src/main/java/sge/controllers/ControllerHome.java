package sge.controllers;

import sge.Administrador;
import sge.Reportes.Reporte;
import sge.Web.logger;
import sge.clientes.Cliente;

import sge.dispositivos.TipoDeDispositivo;
import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;

import sge.persistencia.json.CargaDatosWrapper;
import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoClientes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.nio.charset.Charset;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class ControllerHome {
	public static boolean lanzarCartel = false;
	public static boolean lanzaAdvertenciaDni = false;
	public static boolean lanzaAltaOK = false;
	public static CargaDatosWrapper cargador = new CargaDatosWrapper();
	public static RepoClientes repoClientes = RepoClientes.getInstance();
	public static RepoAdmins repoAdmins = RepoAdmins.getInstance();
	
	public static ModelAndView redirectLogin(Request req, Response res) {
		res.redirect("/login");
		HashMap<String, Object> viewModel = new HashMap<>();
		return new ModelAndView(viewModel, "");
	}
	
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

	public static ModelAndView intentarLoguear(Request req, Response res) {

		List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

		Map<String, String> params = toMap(pairs);

		String email = params.get("email");
		int password = Integer.parseInt(params.get("password"));

		logger session = new logger();
		HashMap<String, Object> viewModel = new HashMap<>();

		if (session.checkPass(email, password, "admin")) {

			Optional<Administrador> admin = repoAdmins.get().stream()
					.filter(c -> c.email().equals(email) && c.pass().equals(password)).findFirst();

			if (admin.isPresent()) {
				req.session(true);
				req.session().attribute("admin", admin.get());
				res.redirect("/administrador/consumos");
			} else {
				res.redirect("/login");
			}
			

			return new ModelAndView(viewModel, "");

		} else if (session.checkPass(email, password, "cliente")) {
			Optional<Cliente> cliente = repoClientes.get().stream()
					.filter(c -> c.getEmail().equals(email) && c.pass().equals(password)).findFirst();

			if (cliente.isPresent()) {
				req.session(true);
				req.session().attribute("cliente", cliente.get());
				res.redirect("/cliente/hogar/dispositivos");
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
	
	public static void autenticarAdmin(Request req, Response res, HashMap<String, Object> viewModel) {
		Object admin = req.session().attribute("admin");
		if (admin == null)
			res.redirect("/login");
		viewModel.put("admin", admin);
	}

	private static Map<String, String> toMap(List<NameValuePair> pairs){
	    Map<String, String> map = new HashMap<>();
	    for(int i=0; i<pairs.size(); i++){
	        NameValuePair pair = pairs.get(i);
	        map.put(pair.getName(), pair.getValue());
	    }
	    return map;

	}

}
