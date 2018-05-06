package sge.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileIO {

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
}
