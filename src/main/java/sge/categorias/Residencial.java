package sge.categorias;

import java.time.temporal.ValueRange;

public class Residencial implements Categoria {
	private double cargoFijo;
	private double cargoPorkWh;
	private ValueRange rango;
	
	public Residencial(double cargoFijo, double cargoPorkWh, int min, int max) {
		this.cargoFijo = cargoFijo;
		this.cargoPorkWh = cargoPorkWh;
		this.rango = ValueRange.of(min, max);
	}
	 
	public double calcularConsumos(double KWConsumidos) {
		return cargoFijo + KWConsumidos * cargoPorkWh;
	}
	public boolean perteneceAEstaCategoria(long consumo) {
		return rango.isValidValue(consumo);
	}
}
