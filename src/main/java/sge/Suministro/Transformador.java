package sge.Suministro;

import java.util.ArrayList;
import java.util.List;

import sge.Coordenates;
import sge.clientes.Cliente;

public class Transformador {
	List<Cliente> clientes = new ArrayList<Cliente>();
	Coordenates posicion;
	
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
