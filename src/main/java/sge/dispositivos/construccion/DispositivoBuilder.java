package sge.dispositivos.construccion;

import sge.dispositivos.TipoDeDispositivo;

public interface DispositivoBuilder<T> {
	public T create(String nombre, double consumoKWxHora, TipoDeDispositivo tipo);
}
