package sge.simplex;

import sge.dispositivos.Dispositivo;

public final class ConsumoYDispositivo {
	public Double getConsumo() {
		return consumo;
	}

	public Dispositivo getDispositivo() {
		return disp;
	}

	private final Double consumo;
	private final Dispositivo disp;

	public ConsumoYDispositivo(Double consumo, Dispositivo disp) {
		this.consumo = consumo;
		this.disp = disp;
	}
}