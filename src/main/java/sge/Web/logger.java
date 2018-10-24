package sge.Web;

import java.io.FileNotFoundException;

import sge.persistencia.json.CargaDatosWrapper;
import sge.persistencia.repos.RepoClientes;

public class logger {
	public CargaDatosWrapper cargador = new CargaDatosWrapper();
	public RepoClientes repoClientes = RepoClientes.getInstance();
	
	public boolean checkPass(String email, int pass){
		
		 try {
			 cargador.cargarClientes();				
			return repoClientes.get().stream().anyMatch(unCliente->unCliente.email().equals(email) && unCliente.pass().equals(pass));								
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
