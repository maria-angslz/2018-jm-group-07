package Fixture;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mockito;

import sge.Documento;
import sge.TipoDocumento;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.construccion.Factory;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.dispositivos.inteligentes.DispositivoInteligenteFisico;
import sge.estados.Encendido;
import sge.persistencia.repos.RepoClientes;
import sge.dispositivos.estandar.DispositivoEstandar;;

public class FSimplex {
	
	public Cliente clienteConUnAire;
	public List<DispositivoInteligente> unAire = new ArrayList<DispositivoInteligente>();
	public Cliente clienteConCuatroAires;
	public List<DispositivoInteligente> cuatroAires = new ArrayList<DispositivoInteligente>();
	
	@Before
	public void Init() {
		Categoria r3 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		DispositivoInteligente aire = Factory.Inteligente().AireAcondicionado(1.613);
		DispositivoInteligenteFisico mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		aire.setDispositivoFisico(mockDispositivoFisico);
		aire.cambiarEstado(new Encendido());
		List<DispositivoEstandar> estandares = new ArrayList<DispositivoEstandar>();
		unAire.add(aire);
		clienteConUnAire = new Cliente( "Pepe Mitre" ,
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 241", "01149231234", r3, estandares, unAire);
		clienteConUnAire.setSimplexAutomatico(true);
		RepoClientes.getInstance().agregar(clienteConUnAire);
//		List<DispositivoInteligente> inteligentes2 = new ArrayList<DispositivoInteligente>();
		cuatroAires.add(aire);
		cuatroAires.add(aire);
		cuatroAires.add(aire);
		cuatroAires.add(aire);
		clienteConCuatroAires = new Cliente( "Pepe Mitre" ,
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 241", "01149231234", r3, estandares, cuatroAires);
		
	}
}
