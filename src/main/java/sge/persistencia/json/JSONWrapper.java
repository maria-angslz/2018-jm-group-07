package sge.persistencia.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoClientes;
import sge.persistencia.repos.RepoDispositivos;
import sge.Administrador;
import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.persistencia.AlmacenamientoPersistente;

public class JSONWrapper implements AlmacenamientoPersistente {
	private String archivoDispositivos = ".\\src\\main\\resources\\Dispositivos.json";
	private String archivoAdmins = ".\\src\\main\\resources\\Administradores.json";
	private String archivoClientes = ".\\src\\main\\resources\\Clientes.json";
	private Gson gson = new GsonBuilder().create();
	private static JSONWrapper instancia;

	public static AlmacenamientoPersistente getInstance() {
		if (instancia == null)
			instancia = new JSONWrapper();
		return instancia;
	}

	public String stringFromFile(String filename) throws FileNotFoundException {
		File file = Paths.get(filename).toFile();
		Scanner lector = new Scanner(file);
		lector.useDelimiter("\\A");
		String contenido = lector.next();
		lector.close();
		return contenido;
	}

	public void stringToFile(String filename, String content) throws IOException {
		Files.write(Paths.get(filename), content.getBytes());
	}

	public <T> List<T> cargar(String file) throws FileNotFoundException {
		return 
			gson.fromJson(stringFromFile(file),
				new TypeToken<List<T>>() {}.getType());
	}

	public <T> void guardar(String file, List<T> lista) throws IOException {
		stringToFile(file, gson.toJson(lista));
	}
	public void cargarDispositivos() throws FileNotFoundException {
		List<Dispositivo> dispositivos = cargar(archivoDispositivos);
		RepoDispositivos.getInstance().addAll(dispositivos);
	}

	public void guardarDispositivos() throws IOException {
		guardar(archivoDispositivos, RepoDispositivos.getInstance().get());
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

}