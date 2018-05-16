package sge.persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AlmacenamientoPersistente {
	public void cargarAdmins() throws FileNotFoundException ;

	public void guardarAdmins() throws IOException;

	public void cargarClientes() throws FileNotFoundException ;

	public void guardarClientes() throws IOException;
 
	public void cargarCategorias() throws FileNotFoundException ;

	public void guardarCategorias() throws IOException;
}
