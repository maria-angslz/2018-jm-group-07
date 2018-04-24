import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import sge.Documento;
import sge.TipoDocumento;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;

public class ClienteTest {

	Cliente clienteSinDispositivos;
	Cliente clienteConUnDispositivo;
	Cliente clienteConDosDispositivos;

	@Before
	public void init() {
		Dispositivo tv = new Dispositivo("TV", 10.5, false);
		Dispositivo heladera = new Dispositivo("Heladera", 10.5, true);
		Categoria r1 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		ArrayList<Dispositivo> dosDispositivos = new ArrayList<Dispositivo>();
		dosDispositivos.add(tv);
		dosDispositivos.add(heladera);
		clienteConDosDispositivos = new Cliente(new String[] { "Martin" }, new String[] { "Perez" },
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r1, dosDispositivos);
		ArrayList<Dispositivo> sinDispositivos = new ArrayList<Dispositivo>();
		clienteSinDispositivos = new Cliente(new String[] { "Juan" }, new String[] { "Lopez" },
				new Documento(40732178, TipoDocumento.DNI), "Santa Fe 1781", "01141131234", r1, sinDispositivos);
		ArrayList<Dispositivo> unDispositivo = new ArrayList<Dispositivo>();
		unDispositivo.add(tv);
		clienteConUnDispositivo = new Cliente(new String[] { "Pepe" }, new String[] { "Mitre" },
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 241", "01149231234", r1, unDispositivo);
	}

	@Test
	public void testCantidadTotalDeDispositivos() {
		assertEquals("El cliente debe tener dos dispositivos", 2,
				clienteConDosDispositivos.cantidadDispositivosTotal());
		assertEquals("El cliente debe tener un dispositivos", 1, clienteConUnDispositivo.cantidadDispositivosTotal());
		assertEquals("El cliente debe no tener dispositivos", 0, clienteSinDispositivos.cantidadDispositivosTotal());
	}

	@Test
	public void testCantidadDeDispositivosEncendidos() {
		assertEquals("El cliente debe tener solo un dispositivo encendido", 1,
				clienteConDosDispositivos.cantidadDispositivosEncendidos());
		assertEquals("El cliente no debe tener dispositivos encendidos", 0,
				clienteConUnDispositivo.cantidadDispositivosEncendidos());
		assertEquals("El cliente no debe tener dispositivos encendidos", 0,
				clienteSinDispositivos.cantidadDispositivosEncendidos());
	}

	@Test
	public void testCantidadDispositivosApagados() {
		assertEquals("El cliente debe tener solo un dispositivo apagado", 1,
				clienteConDosDispositivos.cantidadDispositivosApagados());
		assertEquals("El cliente debe tener solo un dispositivo apagado", 1,
				clienteConUnDispositivo.cantidadDispositivosApagados());
		assertEquals("El cliente no debe tener dispositivos apagados", 0,
				clienteSinDispositivos.cantidadDispositivosApagados());
	}

	@Test
	public void testAlgunDispositivoEstaEncendido() {
		assertTrue("El cliente debe al menos un dispositivo encendido",
				clienteConDosDispositivos.algunDispositivoEstaEncendido());
		assertFalse("El cliente debe al menos un dispositivo encendido",
				clienteConUnDispositivo.algunDispositivoEstaEncendido());
		assertFalse("El cliente debe al menos un dispositivo encendido",
				clienteSinDispositivos.algunDispositivoEstaEncendido());
	}

	@Test
	public void testFacturacionAproximadaClienteCategoriaR1ConDosDispositivos() {

		clienteConDosDispositivos.getDispositivos().stream().forEach(disp -> disp.setConsumoDeEsteMes(100));

		assertEquals(
				"El cliente de categoria R1 con dos dispositivos de consumo mensual igual a 100 kW tiene una facturacion aproximada de 257.62",
				(200 * 0.681) + 60.71, clienteConDosDispositivos. facturacionAproximada(), 0.05);

	}

	@Test
	public void testFacturacionAproximadaClienteCategoriaR1ConUnDispositivos() {

		clienteConUnDispositivo.getDispositivos().stream().forEach(disp -> disp.setConsumoDeEsteMes(100));

		assertEquals(
				"El cliente de categoria R1 con un dispositivo de consumo mensual igual a 100 kW tiene una facturacion aproximada de 98.81",
				(100 * 0.681) + 60.71, clienteConUnDispositivo. facturacionAproximada(), 0.05);

	}

	@Test
	public void testClienteSinDispositivosFacturacionAproximadaCostoFijo() {

		assertEquals("testClienteSinDispositivosFacturacionAproximadaCostoFijo", 60.71,
				clienteSinDispositivos. facturacionAproximada(), 0.05);
	}

}
