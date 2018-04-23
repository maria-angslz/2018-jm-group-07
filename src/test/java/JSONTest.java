import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import sge.Administrador;
import sge.Documento;
import sge.TipoDocumento;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.persistencia.ServiceLocator;
import sge.persistencia.json.JSONWrapper;
import sge.persistencia.repos.RepoClientes;
import sge.persistencia.repos.RepoDispositivos;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JSONTest {

	@Mock
	JSONWrapper mockWrapper;
	List<Dispositivo> dispositivos;
	List<ArrayList<Dispositivo>> listasDeDispositivos;
	List<Cliente> clientes;
	List<ArrayList<Cliente>> listasDeClientes;

	@Before
	public void init() throws FileNotFoundException {
		// Dispositivos
		dispositivos = new ArrayList<Dispositivo>();
		dispositivos.add(new Dispositivo("Microondas", 4.0, true));
		dispositivos.add(new Dispositivo("TV", 5.0, true));
		dispositivos.add(new Dispositivo("Heladera", 40.0, false));
		dispositivos.add(new Dispositivo("Parlante", 2.0, false));
		// Listas de dispositivos
		listasDeDispositivos = new ArrayList<ArrayList<Dispositivo>>();
		listasDeDispositivos.add(new ArrayList<Dispositivo>());
		listasDeDispositivos.add(new ArrayList<Dispositivo>() {
			{
				add(dispositivos.get(0));
			}
		});
		listasDeDispositivos.add(new ArrayList<Dispositivo>() {
			{
				add(dispositivos.get(1));
				add(dispositivos.get(2));
			}
		});
		listasDeDispositivos.add(new ArrayList<Dispositivo>() {
			{
				add(dispositivos.get(3));
			}
		});
		// Le decimos a Mockito que use el cargarDispositivos real
		doCallRealMethod().when(mockWrapper).cargarDispositivos();

		// Clientes
		Categoria r1 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(new String[] { "Martin" }, new String[] { "Perez" },
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r1,
				listasDeDispositivos.get(0)));
		clientes.add(new Cliente(new String[] { "Juan" }, new String[] { "Lopez" },
				new Documento(40732178, TipoDocumento.DNI), "Santa Fe 1781", "01141131234", r1,
				new ArrayList<Dispositivo>()));
		clientes.add(new Cliente(new String[] { "Pepe" }, new String[] { "Mitre" },
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 241", "01149231234", r1,
				listasDeDispositivos.get(1)));
		clientes.add(new Cliente(new String[] { "Jose" }, new String[] { "Hernandez" },
				new Documento(47465078, TipoDocumento.DNI), "Belgrano 2252", "01149212334", r1,
				listasDeDispositivos.get(2)));
		// Listas de Clientes
		listasDeClientes = new ArrayList<ArrayList<Cliente>>();
		listasDeClientes.add(new ArrayList<Cliente>());
		listasDeClientes.add(new ArrayList<Cliente>() {
			{
				add(clientes.get(0));
			}
		});
		listasDeClientes.add(new ArrayList<Cliente>() {
			{
				add(clientes.get(1));
				add(clientes.get(2));
			}
		});
		listasDeClientes.add(new ArrayList<Cliente>() {
			{
				add(clientes.get(3));
			}
		});
		// Le decimos a Mockito que use el cargarClientes real
		doCallRealMethod().when(mockWrapper).cargarClientes();
		ServiceLocator.getInstance().setAlmacenamiento(mockWrapper);
	}

	private <T> void makeJSONLoad(List<T> dispositivos) throws FileNotFoundException {
		// Hacemos que cuando se llama a cargar del mock devuelve una lista
		// hardcodeada
		when(mockWrapper.cargar(any())).thenAnswer(new Answer<List<T>>() {
			@Override
			public List<T> answer(InvocationOnMock invocation) throws Throwable {
				return dispositivos;
			}
		});
	}

	@Test
	public void testCargarDispositivos() throws Exception {
		// Cambiamos el servicio de almacenamiento
		RepoDispositivos repo = RepoDispositivos.getInstance();
		// Testeamos que se cargan correctamente
		// todas nuestras listas de testing
		listasDeDispositivos.forEach(dispositivos -> {
			try {
				repo.reset();
				makeJSONLoad(dispositivos);
				ServiceLocator.getInstance().getAlmacenamiento().cargarDispositivos();
				assertEquals("La lista de clientes no se cargo correctamente", dispositivos, repo.get());
			} catch (FileNotFoundException e) {
				fail("No se deberia lanzar excepciones aqui");
			}
		});
		// Verificamos que se llamo a cargar n veces
		verify(mockWrapper, times(listasDeDispositivos.size())).cargar(any());
		RepoDispositivos dispositivos = RepoDispositivos.getInstance();
	}

	@Test
	public void testCargarClientes() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		// Cambiamos el servicio de almacenamiento
		RepoClientes repo = RepoClientes.getInstance();
		// Testeamos que se cargan correctamente
		// todas nuestras listas de testing
		listasDeClientes.forEach(clientes -> {
			try {
				repo.reset();
				makeJSONLoad(clientes);
				ServiceLocator.getInstance().getAlmacenamiento().cargarClientes();
				assertEquals("La lista de clientes no se cargo correctamente", clientes, repo.get());
			} catch (FileNotFoundException e) {
				fail("No se deberia lanzar excepciones aqui");
			}
		});
		// Verificamos que se llamo a cargar n veces
		verify(mockWrapper, times(listasDeClientes.size())).cargar(any());
	}
}
