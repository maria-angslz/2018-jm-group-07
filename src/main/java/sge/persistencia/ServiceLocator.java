package sge.persistencia;

import sge.persistencia.json.JSONWrapper;

public class ServiceLocator {
	private AlmacenamientoPersistente almacenamiento;
	private static ServiceLocator instancia;

	public static ServiceLocator getInstance() {
		if (instancia == null) {
			instancia = new ServiceLocator();
			instancia.almacenamiento = (AlmacenamientoPersistente) new JSONWrapper();
		}
		return instancia;
	}

	public AlmacenamientoPersistente getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(AlmacenamientoPersistente almacenamiento) {
		this.almacenamiento = almacenamiento;
	}
}
