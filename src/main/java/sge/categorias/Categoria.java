package sge.categorias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import sge.clientes.Cliente;

@Entity
public abstract class Categoria {
	
	@Id @GeneratedValue
	private int id;
	
	public abstract double aproximarFacturacion(double KWConsumidos);

	public abstract boolean perteneceAEstaCategoria(double valorConsumido);
}
