package sge.Suministro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import sge.Coordenates;
import sge.SuperClase;
import sge.clientes.Cliente;

@Entity
public class Transformador extends SuperClase
{
	

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="idTransformador")
	List<Cliente> clientes = new ArrayList<Cliente>();

	@Embedded
	Coordenates posicion;


	public Transformador() {
		super();
	}
	
	public Transformador(Coordenates posicion) {
		this.posicion = posicion;
	}
	public double energiaSuministrada(){
		return clientes.stream().mapToDouble(unCliente -> unCliente.consumos()).sum();		
		
	}
	public void setCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public double promedioEnergiaSuministrada() {
		if(this.clientes.size() == 0) {
			return 0;
		}
		else {
			return this.energiaSuministrada() / this.clientes.size();
		}
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setId(int id) {
		this.id = id;
	}
}
