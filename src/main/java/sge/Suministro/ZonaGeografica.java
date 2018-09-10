package sge.Suministro;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import sge.Coordenates;
import sge.clientes.Cliente;


@Entity
public class ZonaGeografica {
	@Id @GeneratedValue
	int id;
	@Transient
	List<Transformador> transformadores;
	int radioCubierto;
	@OneToOne
	Coordenates coordenadaCentral;
	
	public ZonaGeografica(List <Transformador> unTransformador,Coordenates centro ,int radio) {
		transformadores = unTransformador;
		radioCubierto = radio;
		coordenadaCentral = centro;
	}
	
	public double consumoTotal(){
		return transformadores.stream().mapToDouble(unTransformador -> unTransformador.energiaSuministrada()).sum();
		
	}
	
	public void asignarTransformadorAcliente(Cliente cliente, Coordenates coord) {
		transformadores.stream().min((tranf1,tranf2) -> Double.compare(tranf1.posicion.distancia(coord), tranf2.posicion.distancia(coord))).get().setCliente(cliente);
	    
	}
	
	public Boolean pertenece(Coordenates coordenada) {
		return coordenadaCentral.distancia(coordenada) <= radioCubierto;
		
	}
	
}
