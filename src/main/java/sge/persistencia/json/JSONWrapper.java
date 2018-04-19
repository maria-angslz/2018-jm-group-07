package sge.persistencia.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.Administrador;
import sge.dispositivos.Dispositivo;
import sge.persistencia.AlmacenamientoPersistente;
import sge.persistencia.RepoAdmins;
import sge.persistencia.RepoDispositivos;

public class JSONWrapper implements AlmacenamientoPersistente {
	private String archivoDispositivos = ".\\src\\main\\resources\\Dispositivos.json";
	private Gson gson = new GsonBuilder().create();
	private static JSONWrapper instancia;

	public static AlmacenamientoPersistente getInstance() {
		if (instancia == null)
			instancia = new JSONWrapper();
		return instancia;
	}

	public String stringFromFile(String filename) throws FileNotFoundException {
		File file = Paths.get(filename).toFile();
		return new Scanner(file).useDelimiter("\\A").next();
	}

	public void stringToFile(String filename, String content) throws IOException {
		Files.write(Paths.get(filename), content.getBytes());
	}

	public void cargarDispositivos() throws FileNotFoundException {
		List<Dispositivo> dispositivos = 
			gson.fromJson(stringFromFile(archivoDispositivos),
				new TypeToken<List<Dispositivo>>() {}.getType());
		RepoDispositivos.getInstance().addAll(dispositivos);
	}

	public void guardarDispositivos() throws IOException {
		stringToFile(archivoDispositivos, gson.toJson(RepoDispositivos.getInstance().get()));
	}

	public void cargarAdmins() throws FileNotFoundException {
		List<Administrador> admins = gson.fromJson(stringFromFile(archivoDispositivos),
				new TypeToken<List<Administrador>>() {
				}.getType());
		RepoAdmins.getInstance().addAll(admins);
	}

	public void guardarAdmins() throws IOException {
		stringToFile(archivoDispositivos, gson.toJson(RepoAdmins.getInstance().get()));
	}
	
}