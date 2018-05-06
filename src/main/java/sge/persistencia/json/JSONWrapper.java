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

public class JSONWrapper {
	private static Gson gson = new GsonBuilder().create();
	private static JSONWrapper instancia;

	public static AlmacenamientoPersistente getInstance() {
		if (instancia == null)
			instancia = new JSONWrapper();
		return instancia;
	}

	public static String stringFromFile(String filename)  {
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

	public static void stringToFile(String filename, String content) throws IOException {
		Files.write(Paths.get(filename), content.getBytes());
	}

	public static <T> List<T> cargar(String file) throws FileNotFoundException {
		return gson.fromJson(stringFromFile(file), new TypeToken<List<T>>() {
		}.getType());
	}

	public static <T> void guardar(String file, List<T> lista) throws IOException {
		stringToFile(file, gson.toJson(lista));
	}

}