package sge.dispositivos.construccion;

import java.util.List;

import sge.dispositivos.Dispositivo;

public class RepoDispositivosResidenciales {

	static RepoDispositivosResidenciales instancia;
	
	private RepoDispositivosResidenciales() { }
	
	public static RepoDispositivosResidenciales getInstance() {
		if (instancia == null)
			instancia = new RepoDispositivosResidenciales();
		return instancia;
	}
	
	List<Dispositivo> construibles;

	public List<Dispositivo> all() {
		return construibles;
	}

	public void agregar(Dispositivo d) {
		construibles.add(d);
	}
}
