import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import sge.Administrador;
import sge.dispositivos.Dispositivo;
import sge.persistencia.ServiceLocator;
import sge.persistencia.json.JSONWrapper;
import sge.persistencia.repos.RepoDispositivos;

import static org.mockito.Mockito.*;

public class JSONTest {

    @Test
    public void testGuardarDispositivos() throws Exception {
    	// Creamos un mock
    	JSONWrapper mockWrapper = mock(JSONWrapper.class);
    	// Dispositivo para probar
		Dispositivo disp = new Dispositivo("Microondas", 4.0, true);
		List<Dispositivo> ret = new ArrayList<Dispositivo>();
		// Hacemos que cuando se llama a cargar del mock devuelve una lista
		// hardcodeada que devuelve una lista con nuestro dispositivo
		when(mockWrapper.cargar(any())).thenAnswer(new Answer<List<Dispositivo>>() {
		  @Override
		  public List<Dispositivo> answer(InvocationOnMock invocation) throws Throwable {
			ret.add(disp);
		    return ret;
		  }
		});
		// Le decimos a Mockito que use el cargarDispositivos real
		doCallRealMethod().when(mockWrapper).cargarDispositivos();
		// Cambiamos el servicio de almacenamiento
    	ServiceLocator locator = ServiceLocator.getInstance();
    	locator.setAlmacenamiento(mockWrapper);
		locator.getAlmacenamiento().cargarDispositivos();
		// Verificamos que se llamo a cargar y que nuestra 
		// lista termino en el repositorio de dispositivos
    	verify(mockWrapper, times(1)).cargar(any());
    	RepoDispositivos dispositivos = RepoDispositivos.getInstance();
    	assertEquals("", ret, dispositivos.get());
    }
}
