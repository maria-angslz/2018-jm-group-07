package Fixture;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.mockito.Mockito;

import sge.Coordenates;
import sge.Documento;
import sge.TipoDocumento;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.TipoDeDispositivo;
import sge.dispositivos.construccion.Factory;
import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.dispositivos.inteligentes.DispositivoInteligenteFisico;

public class FCliente {
	
	protected Cliente clienteSinDispositivos;
	protected Cliente clienteConUnDispositivo;
	protected Cliente clienteConDosDispositivos;
	protected Cliente clienteMock;
	
	@Before
	public void init() {
		DispositivoInteligente smartTv = Factory.Inteligente().Televisor(0.6);
		DispositivoInteligente pc = Factory.Inteligente().Computadora(0.6);
		DispositivoEstandar heladera = new DispositivoEstandar("Heladera", 0.6, 24, TipoDeDispositivo.Heladera);
		Categoria r3 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		List<DispositivoInteligente> dosDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		List<DispositivoEstandar> sinDispositivosEstandar = new ArrayList<DispositivoEstandar>();
		List<DispositivoInteligente> sinDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		DispositivoInteligenteFisico mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		smartTv.setDispositivoFisico(mockDispositivoFisico);
		smartTv.encender();
		dosDispositivosInteligentes.add(smartTv);
		dosDispositivosInteligentes.add(pc);
		clienteConDosDispositivos = new Cliente("Martin Perez",
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r3,sinDispositivosEstandar, dosDispositivosInteligentes, new Coordenates(1.0,1.0));
		clienteSinDispositivos = new Cliente("Juan Lopez",
				new Documento(40732178, TipoDocumento.DNI), "Santa Fe 1781", "01141131234", r3, sinDispositivosEstandar, sinDispositivosInteligentes, new Coordenates(1.0,1.0));
		List<DispositivoEstandar> unDispositivoEstandar = new ArrayList<DispositivoEstandar>();
		unDispositivoEstandar.add(heladera);
		clienteConUnDispositivo = new Cliente( "Pepe Mitre" ,
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 241", "01149231234", r3, unDispositivoEstandar, sinDispositivosInteligentes, new Coordenates(1.0,1.0));
		clienteMock = Mockito.spy(clienteConUnDispositivo);
		
	}
	
	
}
