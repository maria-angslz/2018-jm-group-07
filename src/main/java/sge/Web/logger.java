package sge.Web;

import java.io.Console;

import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoClientes;

public class logger {
	public RepoClientes repoClientes = RepoClientes.getInstance();
	public RepoAdmins repoAdmins = RepoAdmins.getInstance();
	
	public boolean checkPass(String email, int pass, String usuario){
		
		 if(usuario.equals("cliente")) {
			 	
			  Boolean a = repoClientes.get().stream().anyMatch(unCliente->unCliente.email().equals(email) && unCliente.pass().equals(pass));
			  System.out.println("Econtre el cliente: " + a);
			  return a;
		 }else {
			 
			 Boolean a = repoAdmins.get().stream().anyMatch(unAdmin->unAdmin.email().equals(email) && unAdmin.pass().equals(pass));
			 System.out.println("Econtre el cliente: " + a);
			 return a;
		 }
		
	}
}
