package sge.persistencia;

import java.util.List;
import sge.clientes.Cliente;
import sge.Administrador;
import sge.categorias.Categoria;

public interface AlmacenamientoPersistente {
	public List<Administrador> cargarAdmins();

	public void guardarAdmins(String file, List<Administrador> lista);

	public List<Cliente> cargarClientes();

	public void guardarClientes(String file, List<Cliente> lista);

	public List<Categoria> cargarCategorias();

	public void guardarCategorias(String file, List<Categoria> lista);
}
