package sge.dispositivos;

import sge.SuperClase;

public abstract class Dispositivo extends SuperClase{
	public abstract double consumoKWxHora();
//	protected double minimo;
//	protected double maximo;
	protected TipoDeDispositivo tipo;
	public abstract double consumoMensual();
	public Double getMinimo() {
		return (double) tipo.getMinimo();
	}
	public Double getMaximo() {
		return (double) tipo.getMaximo();
	}
	public boolean mismoModelo(Dispositivo otro) {
		return otro.tipo.getMinimo() == tipo.getMinimo() &&
			otro.tipo.getMaximo() == tipo.getMaximo() &&
			otro.consumoKWxHora() == consumoKWxHora() &&
			otro.getClass() == getClass();
	}
}
