package sge.persistencia.repos;

import java.util.ArrayList;

import sge.categorias.CategoriaResidencial;

public class RepoCatResidenciales extends RepoGenerico<CategoriaResidencial> {
	private static RepoCatResidenciales instancia;

	public static RepoCatResidenciales getInstance() {
		if (instancia == null) {
			instancia = new RepoCatResidenciales();
			instancia.lista = new ArrayList<CategoriaResidencial>();
		}
		return instancia;
	}
}
