package sge.persistencia.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.Administrador;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.persistencia.AlmacenamientoPersistente;
import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoCatResidenciales;
import sge.persistencia.repos.RepoClientes;

public class JSONWrapper implements AlmacenamientoPersistente {
	private String archivoAdmins = ".\\src\\main\\resources\\Administradores.json";
	private String archivoClientes = ".\\src\\main\\resources\\Clientes.json";
	private String archivoCategoriasResidenciales = ".\\src\\main\\resources\\CategoriasResidenciales.json";
	private Gson gson = new GsonBuilder().create();
	private static JSONWrapper instancia;

	public static AlmacenamientoPersistente getInstance() {
		if (instancia == null)
			instancia = new JSONWrapper();
		return instancia;
	}

	public String stringFromFile(String filename)  {
		try {
			File file = Paths.get(filename).toFile();
			Scanner lector = new Scanner(file);
			lector.useDelimiter("\\A");
			String contenido = lector.next();
			lector.close();
			return contenido;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stringToFile(String filename, String content) throws IOException {
		Files.write(Paths.get(filename), content.getBytes());
	}

	public <T> List<T> cargar(String file) throws FileNotFoundException {
		return gson.fromJson(stringFromFile(file), new TypeToken<List<T>>() {
		}.getType());
	}

	public <T> void guardar(String file, List<T> lista) throws IOException {
		stringToFile(file, gson.toJson(lista));
	}

	public void cargarAdmins() throws FileNotFoundException {
		List<Administrador> admins = cargar(archivoAdmins);
		RepoAdmins.getInstance().addAll(admins);
	}

	public void guardarAdmins() throws IOException {
		guardar(archivoAdmins, RepoAdmins.getInstance().get());
	}

	public void cargarClientes() throws FileNotFoundException {
		List<Cliente> clientes = cargar(archivoClientes);
		RepoClientes.getInstance().addAll(clientes);
	}

	public void guardarClientes() throws IOException {
		guardar(archivoClientes, RepoClientes.getInstance().get());
	}

	public void cargarCategorias() throws FileNotFoundException {
		List<CategoriaResidencial> categorias = cargar(archivoCategoriasResidenciales);
		RepoCatResidenciales.getInstance().addAll(categorias);
	}

	public void guardarCategorias() throws IOException {
		guardar(archivoCategoriasResidenciales, RepoCatResidenciales.getInstance().get());
	}

}