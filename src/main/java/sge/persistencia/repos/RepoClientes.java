package sge.persistencia.repos;

import java.util.ArrayList;

import sge.clientes.Cliente;

public class RepoClientes extends RepoGenerico<Cliente> {
	private static RepoClientes instancia;

	public static RepoClientes getInstance() {
		if (instancia == null) {
			instancia = new RepoClientes();
			instancia.lista = new ArrayList<Cliente>();
		}
		return instancia;
	}
}
