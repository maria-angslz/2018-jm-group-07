package sge.persistencia.json;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.persistencia.FileIO;

public class JSONWrapper {
	private static Gson gson = new GsonBuilder().create();
	private static JSONWrapper instancia;

	public static JSONWrapper getInstance() {
		if (instancia == null)
			instancia = new JSONWrapper();
		return instancia;
	}

	public static <T> List<T> cargar(String file) {
		return gson.fromJson(FileIO.stringFromFile(file), new TypeToken<List<T>>() {}.getType());
	}

	public static <T> void guardar(String file, List<T> lista) {
		try {
			FileIO.stringToFile(file, gson.toJson(lista));
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}