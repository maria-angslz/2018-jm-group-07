package sge.persistencia.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.categorias.Categoria;
import sge.estados.EstadoDispositivo;
import sge.persistencia.FileIO;

public class JSONWrapper {
	private static Gson gson = new GsonBuilder().
			registerTypeAdapter(Categoria.class,new InterfaceAdapter<Categoria>()).
			registerTypeAdapter(EstadoDispositivo.class,new InterfaceAdapter<EstadoDispositivo>()).
			setPrettyPrinting().
			create();
	private static JSONWrapper instancia;

	public static JSONWrapper getInstance() {
		if (instancia == null)
			instancia = new JSONWrapper();
		return instancia;
	}

	public static <T> List<T> cargarConClase(String file, Class<T[]> clazz) {
		return Arrays.asList(gson.fromJson(FileIO.stringFromFile(file), clazz));
	}

	public static <T> void guardar(String file, List<T> lista) {
		try {
			FileIO.stringToFile(file, gson.toJson(lista));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}