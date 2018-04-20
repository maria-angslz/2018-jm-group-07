import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import sge.clientes.*;
import sge.categorias.*;
import sge.dispositivos.*;
import sge.Documento;
import sge.TipoDocumento;

public class ClienteTest {
	
	Cliente cliente;
	
	@Before
	public void  init() {
		ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
		dispositivos.add(new Dispositivo("TV", 10.5, false));
		dispositivos.add(new Dispositivo("Heladera", 10.5, true));
		cliente = new Cliente(new String[]{"Martin"},new String[]{"Perez"},new Documento(40732178,TipoDocumento.DNI),"Belgrano 2251","",new Residencial(60.71, 0.681, 325, 400),dispositivos);
	}

	@Test
	public void testCantidadTotalDeDispositivos() {
		assertEquals("El cliente debe tener dos dispositivos",2,cliente.cantidadDispositivosTotal());
	}
	
	@Test
	public void testCantidadDeDispositivosEncendidos() {
		assertEquals("El cliente debe tener solo un dispositivo encendido",1,cliente.cantidadDispositivosEncendidos());
	}
	
	@Test
	public void testCantidadDispositivosApagados() {
		assertEquals("El cliente debe tener solo un dispositivo apagado",1,cliente.cantidadDispositivosApagados());
	}
	
	
	@Test
	public void testAlgunDispositivoEstaEncendido() {
		assertTrue("El cliente debe al menos un dispositivo encendido",cliente.algunDispositivoEstaEncendido());
	}


}
