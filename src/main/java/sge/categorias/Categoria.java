package sge.categorias;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Categoria {
	
	@Id @GeneratedValue
	private int id;
	
	public abstract double aproximarFacturacion(double KWConsumidos);

	public abstract boolean perteneceAEstaCategoria(double valorConsumido);
}
