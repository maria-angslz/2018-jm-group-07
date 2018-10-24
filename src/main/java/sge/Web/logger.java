package sge.Web;

import java.io.FileNotFoundException;

import sge.persistencia.json.CargaDatosWrapper;
import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoClientes;

public class logger {
	public CargaDatosWrapper cargador = new CargaDatosWrapper();
	public RepoClientes repoClientes = RepoClientes.getInstance();
	public RepoAdmins repoAdmins = RepoAdmins.getInstance();
	
	public boolean checkPass(String email, int pass, String usuario){
		
		 try {
			 if(usuario.equals("cliente")) {
				 cargador.cargarClientes();	
				 return repoClientes.get().stream().anyMatch(unCliente->unCliente.email().equals(email) && unCliente.pass().equals(pass));
			 }else {
				 cargador.cargarAdmins();
				 return repoAdmins.get().stream().anyMatch(unAdmin->unAdmin.email().equals(email) && unAdmin.pass().equals(pass));
			 }						
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
