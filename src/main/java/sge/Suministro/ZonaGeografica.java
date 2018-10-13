package sge.Suministro;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import sge.Coordenates;
import sge.SuperClase;
import sge.clientes.Cliente;


@Entity
public class ZonaGeografica extends SuperClase
{
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="idZona")
	List<Transformador> transformadores;
	int radioCubierto;
	@Embedded
	Coordenates coordenadaCentral;
	
	public ZonaGeografica() {
		super();
	}
	
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
	
	public List<Transformador> transformadores(){
		return transformadores;
	}
	
	public void agregarTransformador(Transformador transf) {
		transformadores.add(transf);
	}
	
}
