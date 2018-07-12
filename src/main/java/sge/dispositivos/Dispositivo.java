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
}
