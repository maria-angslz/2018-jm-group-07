package sge.Suministro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import sge.Coordenates;
import sge.clientes.Cliente;

@Entity
public class Transformador {
	@Transient
	List<Cliente> clientes = new ArrayList<Cliente>();
	@OneToOne
	Coordenates posicion;
	@ManyToOne
	int id_zona;
	
	public Transformador(Coordenates posicion) {
		this.posicion = posicion;
	}
	public double energiaSuministrada(){
		return clientes.stream().mapToDouble(unCliente -> unCliente.consumos()).sum();		
		
	}
	public void setCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
}
