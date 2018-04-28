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
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import sge.persistencia.ServiceLocator;

import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoCatResidenciales;
import sge.persistencia.repos.RepoClientes;

@RunWith(MockitoJUnitRunner.class)
public class JSONTest extends Fixture.FJson{

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
	public void testCargarAdmins() throws FileNotFoundException {
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
