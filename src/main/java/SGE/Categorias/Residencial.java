package SGE.Categorias;

import java.time.temporal.ValueRange;

public class Residencial implements Categoria {
	double cargoFijo;
	double cargoPorkWh;
	ValueRange rango;
	
	Residencial(double cargoFijo, double cargoPorkWh, ValueRange rango) {
		this.cargoFijo = cargoFijo;
		this.cargoPorkWh = cargoPorkWh;
		this.rango = rango;
	}
	Residencial(double cargoFijo, double cargoPorkWh, int min, int max) {
		this(cargoFijo, cargoPorkWh, ValueRange.of(min, max));
	}
}
