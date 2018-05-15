import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;



import org.junit.Test;

import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.dispositivos.Dispositivo;
import sge.dispositivosEstandar.DispositivoEstandar;
import sge.persistencia.repos.RepoCatResidenciales;


public class ClienteTest extends Fixture.FCliente{
	
	@Test
	public void testCantidadTotalDeDispositivos() {
		assertEquals("El cliente debe tener dos dispositivos", 2,
				clienteConDosDispositivos.cantidadDispositivosTotal());
		
	}
	
	@Test
	public void testCantidadTotalDeDispositivosDeClienteSinDispositivos() {
		assertEquals("El cliente debe no tener dispositivos", 0, clienteSinDispositivos.cantidadDispositivosTotal());
	}

	@Test
	public void testCantidadDeDispositivosEncendidosDeClienteConDispositivos() {
		assertEquals("El cliente debe tener solo un dispositivo encendido", 1,
				clienteConDosDispositivos.cantidadDispositivosEncendidos());
	}
	
	@Test
	public void testCantidadDeDispositivosEncendidosDeClienteSinDispositivos() {
		assertEquals("El cliente no debe tener dispositivos encendidos", 0,
				clienteSinDispositivos.cantidadDispositivosEncendidos());
	}

	@Test
	public void testCantidadDispositivosApagadosDeClienteConDispositivos() {
		assertEquals("El cliente no debe tenerun dispositivo apagado, ya que el SmartTv esta encendido, y la heladera es tipo Estandar", 0,
				clienteConDosDispositivos.cantidadDispositivosApagados());
	}
	
	@Test
	public void testCantidadDispositivosApagadosDeClienteSinDispositivos() {
		assertEquals("El cliente no debe tener dispositivos apagados", 0,
				clienteSinDispositivos.cantidadDispositivosApagados());
	}

	@Test
	public void testAlgunDispositivoEstaEncendidoDeClienteConDispositivos() {
		assertTrue("El cliente debe tener al menos un dispositivo encendido",
				clienteConDosDispositivos.algunDispositivoEstaEncendido());
	}
	
	@Test
	public void testAlgunDispositivoEstaEncendidoDeClienteSinDispositivos() {
		assertFalse("El cliente no debe tener ni un dispositivo encendido",
				clienteSinDispositivos.algunDispositivoEstaEncendido());
	}

	@Test
	public void testFacturacionAproximadaClienteCategoriaR3ConDosDispositivos() {
		//clienteConDosDispositivos.getDispositivos().stream().forEach(disp -> disp.setConsumoDeEsteMes(175));
		assertEquals(
				"El cliente de categoria R3 con dos dispositivos de consumo 0.6 kW/h tiene una facturacion aproximada de 10357.43",
				649.094, clienteConDosDispositivos. facturacionAproximada(), 0.05);
	}

	@Test
	public void testFacturacionAproximadaClienteCategoriaR3ConUnDispositivos() {
		//clienteConUnDispositivo.getDispositivos().stream().forEach(disp -> disp.setConsumoDeEsteMes(350));
		assertEquals(
				"El cliente de categoria R3 con un dispositivo de consumo 0.6 kW/h tiene una facturacion aproximada de 5209.07",
				354.902, clienteConUnDispositivo. facturacionAproximada(), 0.05);
	}

	@Test
	public void testClienteSinDispositivosFacturacionAproximadaCostoFijo() {
		assertEquals("testClienteSinDispositivosFacturacionAproximadaCostoFijo", 60.71,
				clienteSinDispositivos. facturacionAproximada(), 0.05);
	}
	
	@Test
	public void testClienteMantieneCategoria() {
		//when(clienteMock.consumoDeEsteMes()).thenReturn(350.0);
		//Categoria r3 = clienteMock.getCategoria();
		//clienteMock.recategorizar();
		Categoria r3 = clienteConUnDispositivo.getCategoria();
		clienteConUnDispositivo.recategorizar();
		assertEquals("El cliente no supera el consumo maximo de su categoria, por ende la mantiene", r3, clienteConUnDispositivo.getCategoria());
	}
	
	@Test
	public void testClienteCambiaDeCategoria() {
		
		//RepoCatResidenciales.getInstance().agregar((CategoriaResidencial)r4);
		//when(clienteMock.consumoDeEsteMes()).thenReturn(425.0);
		Categoria r7 = new CategoriaResidencial(443.59, 0.851, 600, 700);
		RepoCatResidenciales.getInstance().agregar((CategoriaResidencial) r7);
		clienteConDosDispositivos.recategorizar();
		assertEquals("El cliente supera el consumo maximo de su categoria (R3), por ende se recategoriza a R4", r7, clienteConDosDispositivos.getCategoria());
	}
	
	@Test
	public void testLigarModulo() {
		DispositivoEstandar heladera = (DispositivoEstandar) clienteConUnDispositivo.getDispositivos().get(0);
		clienteConUnDispositivo.ligarModuloADispositivo(heladera);
		assertEquals("El cliente tiene 1 dispositivo apagado ya que ahora al dispositivo Heladera (antes tipo Estandar )se le puede preguntar el estado", 1,clienteConUnDispositivo.cantidadDispositivosApagados());
	}

}
