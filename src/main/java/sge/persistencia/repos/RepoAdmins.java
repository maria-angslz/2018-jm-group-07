package sge.persistencia.repos;

import java.util.ArrayList;
import sge.Administrador;

public class RepoAdmins extends RepoGenerico<Administrador> {
	private static RepoAdmins instancia;
	
	public static RepoAdmins getInstance() {
		if (instancia == null) {
			instancia = new RepoAdmins();
			instancia.lista = new ArrayList<Administrador>();
		}
		return instancia; 
	}
}
