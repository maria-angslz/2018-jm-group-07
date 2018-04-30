package sge.categorias;

import java.time.temporal.ValueRange;

import sge.clientes.Cliente;

public class CategoriaResidencial implements Categoria {
	private double cargoFijo;
	private double cargoPorkWh;
	private ValueRange rango;

	public CategoriaResidencial(double cargoFijo, double cargoPorkWh, int min, int max) {
		this.cargoFijo = cargoFijo;
		this.cargoPorkWh = cargoPorkWh;
		this.rango = ValueRange.of(min, max);
	}

	public double aproximarFacturacion(double KWConsumidos) {
		return cargoFijo + KWConsumidos * cargoPorkWh;
	}

	public boolean perteneceAEstaCategoria(double valorConsumido) {
		// Suponemos que se recategoriza a fin de mes
		// Antes de que se resete el consumo de los dispositivos
		return rango.isValidValue((long) valorConsumido);
	}
}
