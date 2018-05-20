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
import sge.dispositivosEstandar.DispositivoEstandar;
import sge.dispositivosInteligentes.DispositivoInteligente;

public class FCliente {
	
	protected Cliente clienteSinDispositivos;
	protected Cliente clienteConUnDispositivo;
	protected Cliente clienteConDosDispositivos;
	protected Cliente clienteMock;
	
	@Before
	public void init() {
		DispositivoInteligente smartTv = new DispositivoInteligente("SmartTV", 0.6);
		DispositivoInteligente pc = new DispositivoInteligente("PC", 0.6);
		DispositivoEstandar heladera = new DispositivoEstandar("Heladera", 0.6, 24);
		Categoria r3 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		List<DispositivoInteligente> dosDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		List<DispositivoEstandar> sinDispositivosEstandar = new ArrayList<DispositivoEstandar>();
		List<DispositivoInteligente> sinDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		smartTv.encender();
		dosDispositivosInteligentes.add(smartTv);
		dosDispositivosInteligentes.add(pc);
		clienteConDosDispositivos = new Cliente("Martin Perez",
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r3,sinDispositivosEstandar, dosDispositivosInteligentes);
		clienteSinDispositivos = new Cliente("Juan Lopez",
				new Documento(40732178, TipoDocumento.DNI), "Santa Fe 1781", "01141131234", r3, sinDispositivosEstandar, sinDispositivosInteligentes);
		List<DispositivoEstandar> unDispositivoEstandar = new ArrayList<DispositivoEstandar>();
		unDispositivoEstandar.add(heladera);
		clienteConUnDispositivo = new Cliente( "Pepe Mitre" ,
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 241", "01149231234", r3, unDispositivoEstandar, sinDispositivosInteligentes);
		clienteMock = Mockito.spy(clienteConUnDispositivo);
		
	}
	
	
}
