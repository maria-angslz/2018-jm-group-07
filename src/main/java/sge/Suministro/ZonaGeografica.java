package sge.Suministro;

import java.util.Comparator;
import java.util.List;

import sge.Coordenates;
import sge.clientes.Cliente;



public class ZonaGeografica {

	List<Transformador> transformadores;
	int radioCubierto;
	Coordenates coordenadaCentral;
	Comparator<Double> comparadorMinimo;
	
	public ZonaGeografica(List <Transformador> unTransformador,Coordenates centro ,int radio) {
		transformadores = unTransformador;
		radioCubierto = radio;
		coordenadaCentral = centro;
	}
	
	public double consumoTotal(){
		return transformadores.stream().mapToDouble(unTransformador -> unTransformador.energiaSuministrada()).sum();
		
	}
	
	public void asignarTransformadorAcliente(Cliente cliente, Coordenates coord) {
		transformadores.stream().min((tranf1,tranf2) -> Double.compare(tranf1.distanciaA(coord), tranf2.distanciaA(coord))).get().setCliente(cliente);
		
	    
	}
	
	public Boolean pertenece(Coordenates coordenada) {
		return Math.sqrt( Math.pow(coordenadaCentral.X() - coordenada.X(), 2)  + Math.pow(coordenadaCentral.Y() - coordenada.Y(),2)) <= radioCubierto;
		
	}
	
}
