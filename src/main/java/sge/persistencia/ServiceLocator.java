package sge.persistencia;

import sge.persistencia.json.*;

public class ServiceLocator {
	private AlmacenamientoPersistente almacenamiento;
	private static ServiceLocator instancia;

	public static ServiceLocator getInstance() {
		if (instancia == null) {
			instancia = new ServiceLocator();
			instancia.almacenamiento = new JSONWrapper();
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
