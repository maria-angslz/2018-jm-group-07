package sge.dispositivos.construccion;

public interface DispositivoBuilder<T> {
	public T create(String nombre, double consumoKWxHora, double maximo, double minimo);
}
