package sge.categorias;

import javax.persistence.OneToMany;

import sge.clientes.Cliente;

public abstract class Categoria {
	@OneToMany
	Cliente cliente;
	
	public abstract double aproximarFacturacion(double KWConsumidos);

	public abstract boolean perteneceAEstaCategoria(double valorConsumido);
}
