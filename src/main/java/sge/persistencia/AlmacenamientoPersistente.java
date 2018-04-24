package sge.persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AlmacenamientoPersistente {
	public void cargarDispositivos() throws FileNotFoundException;

	public void guardarDispositivos() throws IOException;

	public void cargarAdmins() throws FileNotFoundException;

	public void guardarAdmins() throws IOException;

	public void cargarClientes() throws FileNotFoundException;

	public void guardarClientes() throws IOException;
}
