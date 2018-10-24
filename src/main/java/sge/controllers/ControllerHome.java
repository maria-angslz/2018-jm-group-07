package sge.controllers;

import sge.Web.logger;
import sge.persistencia.json.CargaDatosWrapper;
import sge.persistencia.repos.RepoClientes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        	
        	return new ModelAndView(viewModel, "ViewReporte.html");
        	
        }
        else {
        	lanzarCartel = true;
        	res.redirect("/login");       	
        	
        	return new ModelAndView(viewModel, "login.hbs");
        	
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
		

