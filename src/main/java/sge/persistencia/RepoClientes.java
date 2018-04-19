package sge.persistencia;

import java.util.List;

import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;

public class RepoClientes {
	private List<Cliente> dispositivos;
	private static RepoClientes instancia;
	
	public static RepoClientes getInstance() {
		if (instancia == null)
			instancia = new RepoClientes();
		return instancia; 
	}
}
