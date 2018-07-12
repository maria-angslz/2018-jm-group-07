package sge.dispositivos;

public abstract class Dispositivo {
	public abstract double consumoKWxHora();
	protected double minimo;
	protected double maximo;
	public abstract double consumoMensual();
	public Double getMinimo() {
		return minimo;
	}
	public Double getMaximo() {
		return maximo;
	}
	public boolean mismoModelo(Dispositivo otro) {
		return otro.minimo == minimo &&
			otro.maximo == maximo &&
			otro.consumoKWxHora() == consumoKWxHora() &&
			otro.getClass() == getClass();
	}
}
