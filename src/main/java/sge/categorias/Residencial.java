package sge.categorias;

import java.time.temporal.ValueRange;

public class Residencial implements Categoria {
	private double cargoFijo;
	private double cargoPorkWh;
	private ValueRange rango;
	
	public Residencial(double cargoFijo, double cargoPorkWh, ValueRange rango) {
		this.cargoFijo = cargoFijo;
		this.cargoPorkWh = cargoPorkWh;
		this.rango = rango;
	}
	public Residencial(double cargoFijo, double cargoPorkWh, int min, int max) {
		this(cargoFijo, cargoPorkWh, ValueRange.of(min, max));
	}
}
