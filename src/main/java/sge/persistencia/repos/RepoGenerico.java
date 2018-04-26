package sge.persistencia.repos;

import java.util.ArrayList;
import java.util.List;

public class RepoGenerico<T> {
	protected List<T> lista = new ArrayList<T>();

	public List<T> get() {
		return lista;
	}

	public void agregar(T nuevoDispositivo) {
		lista.add(nuevoDispositivo);
	}

	public void quitar(T nuevoDispositivo) {
		lista.remove(nuevoDispositivo);
	}

	public void addAll(List<T> dispositivosNuevos) {
		lista.addAll(dispositivosNuevos);
	}

	public void reset() {
		lista = new ArrayList<T>();
	}
}