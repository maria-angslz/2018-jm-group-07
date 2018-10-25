package sge.Web;

import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoClientes;

public class logger {
	public RepoClientes repoClientes = RepoClientes.getInstance();
	public RepoAdmins repoAdmins = RepoAdmins.getInstance();
	
	public boolean checkPass(String email, int pass, String usuario){
		
		 if(usuario.equals("cliente")) {
			 	
			  return repoClientes.get().stream().anyMatch(unCliente->unCliente.email().equals(email) && unCliente.pass().equals(pass));
		 }else {
			 
			 return repoAdmins.get().stream().anyMatch(unAdmin->unAdmin.email().equals(email) && unAdmin.pass().equals(pass));
		 }
		
	}
}
