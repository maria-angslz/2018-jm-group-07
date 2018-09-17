package sge.persistencia.repos;

import sge.clientes.Cliente;
import java.util.List;
import java.util.stream.Collectors;

public class RepoClientes extends RepoGenerico<Cliente> {
	private static RepoClientes instancia;

	public static RepoClientes getInstance() {
		if (instancia == null) {
			instancia = new RepoClientes();
		}
		return instancia;
	}
	
	public List<Cliente> obtenerClientesSimplex() {
		return RepoClientes.getInstance().get().stream().filter(cliente -> cliente.getSimplexAutomatico() == 1).collect(Collectors.toList());
	}
}
