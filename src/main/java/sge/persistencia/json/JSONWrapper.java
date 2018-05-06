package sge.persistencia.json;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.Administrador;
import sge.categorias.Categoria;
import sge.clientes.Cliente;
import sge.persistencia.AlmacenamientoPersistente;
import sge.persistencia.FileIO;

public class JSONWrapper implements AlmacenamientoPersistente {
	private static Gson gson = new GsonBuilder().create();
	private static JSONWrapper instancia;
	private String archivoAdmins = ".\\src\\main\\resources\\Administradores.json";
	private String archivoClientes = ".\\src\\main\\resources\\Clientes.json";
	private String archivoCategorias = ".\\src\\main\\resources\\Categorias.json";

	public static AlmacenamientoPersistente getInstance() {
		if (instancia == null)
			instancia = new JSONWrapper();
		return instancia;
	}

	public <T> List<T> cargar(String file) {
		return gson.fromJson(FileIO.stringFromFile(file), new TypeToken<List<T>>() {}.getType());
	}

	public <T> void guardar(String file, List<T> lista) {
		try {
			FileIO.stringToFile(file, gson.toJson(lista));
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Administrador> cargarAdmins() {
		return cargar(archivoAdmins);
	}

	public void guardarAdmins(String file, List<Administrador> lista) {
		guardar(archivoAdmins, lista);
	}

	public List<Cliente> cargarClientes() {
		return cargar(archivoClientes);
	}
	
	public void guardarClientes(String file, List<Cliente> lista) {
		guardar(archivoClientes, lista);
	}
	
	public List<Categoria> cargarCategorias() {
		return cargar(archivoCategorias);
	}
	
	public void guardarCategorias(String file, List<Categoria> lista) {
		guardar(archivoCategorias, lista);
	}
	
}