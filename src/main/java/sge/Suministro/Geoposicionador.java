package sge.Suministro;
import sge.Coordenates;
import sge.clientes.Cliente;
import sge.persistencia.repos.RepoClientes;
import sge.persistencia.repos.RepoZonas;


public class Geoposicionador {
	
	private static Geoposicionador instancia;

	public static Geoposicionador getInstance() {
		if (instancia == null) {
			instancia = new Geoposicionador();
		}
		return instancia;
	}
	
	
	public void ubicarClientes() {
		RepoClientes.getInstance().get().forEach(unCliente -> this.ubicarTransformadorSegunZona(unCliente));
		
		
	}
	
	public void ubicarTransformadorSegunZona(Cliente uncliente) {
		
		this.ubicarZonaSegunCoordenada(this.coordenates(uncliente.domicilio())).asignarTransformadorAcliente(uncliente, this.coordenates(uncliente.domicilio()));
		
	}
	
	public Coordenates coordenates(String domicilio) {
		
		return new Coordenates(1, 1);
		
	}
	
	
	public ZonaGeografica ubicarZonaSegunCoordenada(Coordenates coordenadas) {
		return RepoZonas.getInstance().get().stream().filter(unaZona -> unaZona.pertenece(coordenadas)).findFirst().get();
		
		
	}

}
