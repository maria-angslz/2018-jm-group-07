package sge.persistencia.repos;



import sge.categorias.CategoriaResidencial;

public class RepoCatResidenciales extends RepoGenerico<CategoriaResidencial> {
	private static RepoCatResidenciales instancia;

	public static RepoCatResidenciales getInstance() {
		if (instancia == null) {
			instancia = new RepoCatResidenciales();
		}
		return instancia;
	}
}
