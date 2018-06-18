package sge.persistencia.repos;

import sge.Suministro.ZonaGeografica;

public class RepoZonas extends RepoGenerico<ZonaGeografica> {
	private static RepoZonas instancia;

	public static RepoZonas getInstance() {
		if (instancia == null) {
			instancia = new RepoZonas();
		}
		return instancia;
	}
}


