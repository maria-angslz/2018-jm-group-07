package Fixture;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import sge.Coordenates;
import sge.Documento;
import sge.TipoDocumento;
import sge.Suministro.Geoposicionador;
import sge.Suministro.Transformador;
import sge.Suministro.ZonaGeografica;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.construccion.Factory;
import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.persistencia.repos.RepoClientes;
import sge.persistencia.repos.RepoZonas;

public class FSumistro {

	protected List <Transformador> transformadores;
	protected Cliente clienteConDosDispositivos;
	protected ZonaGeografica capitalFederal;
	protected Transformador unTransformador;
	@Before
	
	public void Init() {
		//////////////cliente///////////////////////
		DispositivoInteligente smartTv = Factory.Inteligente().Televisor(0.6);
//		DispositivoInteligente smartTv = new DispositivoInteligente("SmartTV", 0.6, TipoDeDispositivo.Televisor); //segundo parametro el consumo 
		DispositivoInteligente pc = Factory.Inteligente().Computadora(0.6);
//		DispositivoInteligente pc = new DispositivoInteligente("PC", 0.6, TipoDeDispositivo.Computadora);
		Categoria r3 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		List<DispositivoInteligente> dosDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		List<DispositivoEstandar> sinDispositivosEstandar = new ArrayList<DispositivoEstandar>();
		List<Transformador> transformadores = new ArrayList<Transformador>() ;
		dosDispositivosInteligentes.add(smartTv);
		dosDispositivosInteligentes.add(pc);
		clienteConDosDispositivos = new Cliente("Martin Perez",
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r3,sinDispositivosEstandar, dosDispositivosInteligentes, new Coordenates(1.0,1.0));
		////////////////////////////////////////////
		RepoClientes.getInstance().agregar(clienteConDosDispositivos);		
		

		Coordenates posicionTransf = new Coordenates(1.0, 1.0);
		
		unTransformador = new Transformador(posicionTransf);
		transformadores.add(unTransformador);
		
		Coordenates centroCapital = new Coordenates(0.0, 0.0);
		capitalFederal = new ZonaGeografica(transformadores,centroCapital,10); // el 10 es el radio que abarca
		RepoZonas.getInstance().agregar(capitalFederal);
		Geoposicionador ungeo = new Geoposicionador();
		ungeo.ubicarClientes(); //le seteo a los transformadores sus clientes asociados;
	
		//////////////////////////////////////////////
		
		
		
	}
	
}
