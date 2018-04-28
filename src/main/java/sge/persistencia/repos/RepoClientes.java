package sge.persistencia.repos;


import sge.clientes.Cliente;

public class RepoClientes extends RepoGenerico<Cliente> {
	private static RepoClientes instancia;

	public static RepoClientes getInstance() {
		if (instancia == null) {
			instancia = new RepoClientes();
		}
		return instancia;
	}
}
