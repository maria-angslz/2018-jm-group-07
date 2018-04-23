package sge.categorias;

import sge.clientes.Cliente;

public interface Categoria {
	double aproximarFacturacion(double KWConsumidos);
	boolean perteneceAEstaCategoria(Cliente cliente);
}
