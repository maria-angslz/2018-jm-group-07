package sge.controllers;

import sge.Reportes.Reporte;
import sge.Web.logger;
import sge.clientes.Cliente;
import sge.dispositivos.TipoDeDispositivo;
import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.persistencia.json.CargaDatosWrapper;
import sge.persistencia.repos.RepoClientes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static ModelAndView login(Request req, Response res) {
		
		HashMap<String, Object> viewModel = new HashMap<>();
		if(lanzarCartel) {
			viewModel.put("booleano", true);
			lanzarCartel = false;
		}
		else {
			viewModel.remove("booleano");
		}
				
		return new ModelAndView(viewModel, "login.hbs");
	}
	
	public static ModelAndView principal(Request req, Response res) {
	
		List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

        Map<String, String> params = toMap(pairs);

        String email = params.get("email");
        int password = Integer.parseInt(params.get("password"));
       
        logger session = new logger();
        HashMap<String, Object> viewModel = new HashMap<>();
        
        if(session.checkPass(email,password,"admin")) {
        	
        	viewModel.put("clientes", repoClientes.get());
        	
        	return new ModelAndView(viewModel, "ViewConsumos.hbs");
        	
        } else if(session.checkPass(email,password,"cliente")) {
        	
        	return new ModelAndView(viewModel, "ViewConsumoUltimoPeriodo.hbs");
        	
        }
        else {
        	lanzarCartel = true;
        	res.redirect("/login");       	
        	
        	return new ModelAndView(viewModel, "login.hbs");
        	
        }
        
	}
	
	public static ModelAndView reporte(Request req, Response res) {
		
		HashMap<String, Object> viewModel = new HashMap<>();
		String param = req.queryParams("dniBuscado");
		
		if(lanzaAdvertenciaDni) {
			viewModel.put("booleano", true);
			lanzaAdvertenciaDni = false;
		}
		else {
			viewModel.remove("booleano");
		}
		
		
		if(param == null) {
			return new ModelAndView(viewModel, "ViewReporte.hbs");
		}
		else {	
		
		System.out.println(param);	
			
		String queryString = "SELECT * FROM cliente WHERE documento_numero = :dni";
		
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		
		Query query = entityManager.createNativeQuery(queryString, Cliente.class).setParameter("dni", param);
			
		
		if(query.getResultList().size() != 0) {
			Cliente unCliente = (Cliente) query.getSingleResult();
			Reporte reporte = new Reporte();
			double promedio = reporte.PromedioPorDispositivo(unCliente.getid());		
			
			viewModel.put("clienteEncontrado", unCliente.nombre());
			viewModel.put("consumoCalculado", promedio);
					
			return new ModelAndView(viewModel, "ViewReporte.hbs");
		}
		else {
			lanzaAdvertenciaDni = true;
			res.redirect("/principal/reporte");       	
        	
        	return new ModelAndView(viewModel, "ViewReporte.hbs");
		}			
		}
	}
	
public static ModelAndView alta(Request req, Response res) {
	HashMap<String, Object> viewModel = new HashMap<>();
	
	
	String paramFormato = req.queryParams("formato");
	if(lanzaAltaOK) {
		viewModel.put("boolOK", true);
		lanzaAltaOK = false;
	}
	else {
		viewModel.remove("boolOK");
	}
	
	if(paramFormato == null) {
		return new ModelAndView(viewModel, "ViewDispositivo.hbs");
	}	
	else {
		String paramnombreDisp = req.queryParams("nombreDisp");
		String paramconsumoKwHora = req.queryParams("consumoKwHora");
		String paramtipo = req.queryParams("tipo");
		DispositivoInteligente nuevoDispositivoInt;
		DispositivoEstandar nuevoDispositivoEst;		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		if(paramFormato.equals("Inteligente")) {
			
			nuevoDispositivoInt = new DispositivoInteligente(paramnombreDisp, Double.parseDouble(paramconsumoKwHora), TipoDeDispositivo.valueOf(paramtipo));
			
			transaction.begin();
			entityManager.persist(nuevoDispositivoInt);
			transaction.commit();
			lanzaAltaOK = true;
		}
		else {
			String paramhoras = req.queryParams("horasUso");
			nuevoDispositivoEst = new DispositivoEstandar(paramnombreDisp, Double.parseDouble(paramconsumoKwHora), Integer.parseInt(paramhoras),TipoDeDispositivo.valueOf(paramtipo));
			transaction.begin();
			entityManager.persist(nuevoDispositivoEst);
			transaction.commit();
			lanzaAltaOK = true;
		}
		res.redirect("/principal/altaDispositivo");
		return new ModelAndView(viewModel, "ViewDispositivo.hbs");
	}							
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
		

