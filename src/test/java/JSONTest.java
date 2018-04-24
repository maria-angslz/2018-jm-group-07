import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
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
import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoCatResidenciales;
import sge.persistencia.repos.RepoClientes;

@RunWith(MockitoJUnitRunner.class)
public class JSONTest {

	@Mock
	JSONWrapper mockWrapper;
	List<Dispositivo> dispositivos;
	List<ArrayList<Dispositivo>> listasDeDispositivos;
	List<Cliente> clientes;
	List<ArrayList<Cliente>> listasDeClientes;
	List<Administrador> admins;
	List<ArrayList<Administrador>> listasDeAdmins;
	List<CategoriaResidencial> categorias;
	List<ArrayList<CategoriaResidencial>> listasDeCategorias;

	@Before
	public void init() throws FileNotFoundException {
		// Administradores
		admins = new ArrayList<Administrador>();
		LocalDate now = LocalDate.now();
		admins.add(new Administrador(new String[] { "Juan" }, new String[] { "Perez" }, "Belgrano 2251",
				now.minusMonths(2)));
		admins.add(new Administrador(new String[] { "Pepe" }, new String[] { "Perez" }, "Belgrano 21",
				now.minusMonths(3)));
		admins.add(new Administrador(new String[] { "Martin" }, new String[] { "Perez" }, "Belgrano 251",
				now.minusMonths(4)));
		admins.add(new Administrador(new String[] { "Jose" }, new String[] { "Perez" }, "Belgrano 25",
				now.minusMonths(5)));
		// Listas de administradores
		listasDeAdmins = new ArrayList<ArrayList<Administrador>>();
		listasDeAdmins.add(new ArrayList<Administrador>());
		listasDeAdmins.add(new ArrayList<Administrador>() {
			{
				add(admins.get(0));
			}
		});
		listasDeAdmins.add(new ArrayList<Administrador>() {
			{
				add(admins.get(1));
				add(admins.get(2));
			}
		});
		listasDeAdmins.add(new ArrayList<Administrador>() {
			{
				add(admins.get(3));
			}
		});

		// Categorias
		categorias = new ArrayList<CategoriaResidencial>();
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		categorias.add(new CategoriaResidencial(60.71, 0.681, 325, 400));
		// Listas de categorias
		listasDeCategorias = new ArrayList<ArrayList<CategoriaResidencial>>();
		listasDeCategorias.add(new ArrayList<CategoriaResidencial>());
		listasDeCategorias.add(new ArrayList<CategoriaResidencial>() {
			{
				add(categorias.get(0));
			}
		});
		listasDeCategorias.add(new ArrayList<CategoriaResidencial>() {
			{
				add(categorias.get(1));
				add(categorias.get(2));
			}
		});
		listasDeCategorias.add(new ArrayList<CategoriaResidencial>() {
			{
				add(categorias.get(3));
			}
		});

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
		// Cambiamos el servicio de almacenamiento
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
	public void testCargarAdmins() throws Exception {
		// Le decimos a Mockito que use el cargarDispositivos real
		doCallRealMethod().when(mockWrapper).cargarAdmins();
		RepoAdmins repo = RepoAdmins.getInstance();
		// Testeamos que se cargan correctamente
		// todas nuestras listas de testing
		listasDeAdmins.forEach(admins -> {
			try {
				repo.reset();
				makeJSONLoad(admins);
				ServiceLocator.getInstance().getAlmacenamiento().cargarAdmins();
				assertEquals("La lista de administradores no se cargo correctamente", admins, repo.get());
			} catch (FileNotFoundException e) {
				fail("No se deberia lanzar excepciones aqui");
			}
		});
		// Verificamos que se llamo a cargar n veces
		verify(mockWrapper, times(listasDeAdmins.size())).cargar(any());
	}

	@Test
	public void testCargarClientes() throws Exception {
		// Le decimos a Mockito que use el cargarClientes real
		doCallRealMethod().when(mockWrapper).cargarClientes();
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

	@Test
	public void testCargarCategorias() throws Exception {
		// Le decimos a Mockito que use el cargarCategorias real
		doCallRealMethod().when(mockWrapper).cargarCategorias();
		RepoCatResidenciales repo = RepoCatResidenciales.getInstance();
		// Testeamos que se cargan correctamente
		// todas nuestras listas de testing
		listasDeCategorias.forEach(categorias -> {
			try {
				repo.reset();
				makeJSONLoad(categorias);
				ServiceLocator.getInstance().getAlmacenamiento().cargarCategorias();
				assertEquals("La lista de clientes no se cargo correctamente", categorias, repo.get());
			} catch (FileNotFoundException e) {
				fail("No se deberia lanzar excepciones aqui");
			}
		});
		// Verificamos que se llamo a cargar n veces
		verify(mockWrapper, times(listasDeCategorias.size())).cargar(any());
	}

	@Test
	public void testGuardarClientes() throws Exception {
		// Le decimos a Mockito que use el guardarClientes real
		doCallRealMethod().when(mockWrapper).guardarClientes();
		RepoClientes repo = RepoClientes.getInstance();
		// Testeamos que se guardan correctamente
		// todas nuestras listas de testing
		listasDeClientes.forEach(clientes -> {
			try {
				repo.reset();
				repo.addAll(clientes);
				ServiceLocator.getInstance().getAlmacenamiento().guardarClientes();
				verify(mockWrapper, times(1)).guardar(any(), eq(clientes));
			} catch (IOException e) {
				fail("No se deberia lanzar excepciones aqui");
			}
		});
		// Verificamos que se llamo a guardar n veces
		verify(mockWrapper, times(listasDeClientes.size())).guardar(any(), any());
		;
	}

	@Test
	public void testGuardarAdmins() throws Exception {
		// Le decimos a Mockito que use el guardarAdmins real
		doCallRealMethod().when(mockWrapper).guardarAdmins();
		RepoAdmins repo = RepoAdmins.getInstance();
		// Testeamos que se guardan correctamente
		// todas nuestras listas de testing
		listasDeAdmins.forEach(admins -> {
			try {
				repo.reset();
				repo.addAll(admins);
				ServiceLocator.getInstance().getAlmacenamiento().guardarAdmins();
				verify(mockWrapper, times(1)).guardar(any(), eq(admins));
			} catch (IOException e) {
				fail("No se deberia lanzar excepciones aqui");
			}
		});
		// Verificamos que se llamo a guardar n veces
		verify(mockWrapper, times(listasDeAdmins.size())).guardar(any(), any());
		;
	}

	@Test
	public void testGuardarCategorias() throws Exception {
		// Le decimos a Mockito que use el guardarCategoriass real
		doCallRealMethod().when(mockWrapper).guardarCategorias();
		RepoCatResidenciales repo = RepoCatResidenciales.getInstance();
		// Testeamos que se guardan correctamente
		// todas nuestras listas de testing
		listasDeCategorias.forEach(categorias -> {
			try {
				repo.reset();
				repo.addAll(categorias);
				ServiceLocator.getInstance().getAlmacenamiento().guardarCategorias();
				verify(mockWrapper, times(1)).guardar(any(), eq(categorias));
			} catch (IOException e) {
				fail("No se deberia lanzar excepciones aqui");
			}
		});
		// Verificamos que se llamo a guardar n veces
		verify(mockWrapper, times(listasDeCategorias.size())).guardar(any(), any());
		;
	}
}
