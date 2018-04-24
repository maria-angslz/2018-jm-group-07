package sge.persistencia.repos;

import java.util.ArrayList;
import sge.dispositivos.Dispositivo;

public class RepoDispositivos extends RepoGenerico<Dispositivo> {
	private static RepoDispositivos instancia;

	public static RepoDispositivos getInstance() {
		if (instancia == null) {
			instancia = new RepoDispositivos();
			instancia.lista = new ArrayList<Dispositivo>();
		}
		return instancia;
	}
}
