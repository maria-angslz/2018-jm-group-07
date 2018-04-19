package sge.persistencia;

import java.util.ArrayList;
import java.util.List;

import sge.Administrador;
import sge.dispositivos.Dispositivo;

public class RepoAdmins {
	private List<Administrador> admins;
	private static RepoAdmins instancia;
	
	public static RepoAdmins getInstance() {
		if (instancia == null) {
			instancia = new RepoAdmins();
			instancia.admins = new ArrayList<Administrador>();
		}
		return instancia; 
	}
	public List<Administrador> get() {
		return admins;
	}
	public void agregar(Administrador nuevoDispositivo) {
		admins.add(nuevoDispositivo);
	}
	public void quitar(Administrador nuevoDispositivo) {
		admins.remove(nuevoDispositivo);
	}
	public void addAll(List<Administrador> dispositivosNuevos) {
		admins.addAll(dispositivosNuevos);
	}
}
