
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Fixture.Fdispositivo;

public class DispositivoTest extends Fdispositivo {
	@Test
	public void testEncederAlCumplirseCondicionDada() {
		unaRegla.ejecutar();
		assertTrue("El actuador debe enviar la orden de encender la luz",dispoInteligentes.stream().allMatch(dispositivo -> dispositivo.encendido()));
	}
}
