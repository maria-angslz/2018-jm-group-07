package sge.persistencia.repos;

import java.util.ArrayList;

import sge.categorias.Categoria;

public class RepoCatResidenciales extends RepoGenerico<Categoria> {
	private static RepoCatResidenciales instancia;
	
	public static RepoCatResidenciales getInstance() {
		if (instancia == null) {
			instancia = new RepoCatResidenciales();
			instancia.lista = new ArrayList<Categoria>();
		}
		return instancia; 
	} 
}

