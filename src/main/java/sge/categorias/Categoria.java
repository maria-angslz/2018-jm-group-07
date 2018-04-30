package sge.categorias;

public interface Categoria {
	double aproximarFacturacion(double KWConsumidos);

	boolean perteneceAEstaCategoria(double valorConsumido);
}
