package sge.persistencia;

import java.util.ArrayList;
import java.util.List;
import sge.dispositivos.Dispositivo;

public class RepoDispositivos {
	private List<Dispositivo> dispositivos;
	private static RepoDispositivos instancia;
	
	public static RepoDispositivos getInstance() {
		if (instancia == null) {
			instancia = new RepoDispositivos();
			instancia.dispositivos = new ArrayList<Dispositivo>();
		}
		return instancia; 
	}
	public List<Dispositivo> get() {
		return dispositivos;
	}
	public void agregar(Dispositivo nuevoDispositivo) {
		dispositivos.add(nuevoDispositivo);
	}
	public void quitar(Dispositivo nuevoDispositivo) {
		dispositivos.remove(nuevoDispositivo);
	}
	public void addAll(List<Dispositivo> dispositivosNuevos) {
		dispositivos.addAll(dispositivosNuevos);
	}
}
