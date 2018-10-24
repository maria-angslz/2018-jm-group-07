package sge.controllers;

import sge.Web.logger;
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
	public static ModelAndView login(Request req, Response res) {
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
				"login.html");
	}
	
	public static ModelAndView principal(Request req, Response res) {
				
		List<NameValuePair> pairs = URLEncodedUtils.parse(req.body(), Charset.defaultCharset());

        Map<String, String> params = toMap(pairs);

        String email = params.get("email");
        int password = Integer.parseInt(params.get("password"));
       
        logger session = new logger();
        if(session.checkPass(email,password)) {
        
        	HashMap<String, Object> viewModel = new HashMap<>();
        	return new ModelAndView(
				viewModel, 
				"ViewReporte.html");
        }
        else {
        	HashMap<String, Object> viewModel = new HashMap<>();
        	return new ModelAndView(
    				viewModel, 
    				"login.html");
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
		

