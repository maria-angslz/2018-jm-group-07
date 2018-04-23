package sge.categorias;

import java.time.temporal.ValueRange;

import sge.clientes.Cliente;

public class CategoriaResidencial implements Categoria {
	private double cargoFijo;
	private double cargoPorkWh;
	private ValueRange rango;
	
	public CategoriaResidencial(double cargoFijo, 
			double cargoPorkWh, int min, int max) {
		this.cargoFijo = cargoFijo;
		this.cargoPorkWh = cargoPorkWh;
		this.rango = ValueRange.of(min, max);
	}
	public double aproximarFacturacion(double KWConsumidos) {
		return cargoFijo + KWConsumidos * cargoPorkWh;
	}
	public boolean perteneceAEstaCategoria(Cliente cliente) {
		// Usamos la facturacion del mes pasado
		// porque el mes actual puede no haber terminado
		// (x ej. si preguntamos a ppio. de mes va a tener
		// consumo 0)
		return rango.isValidValue(
				(long) cliente.facturacionAproximadaDelMesPasado());
	}
}
