import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Fixture.Fdispositivo;

public class DispositivoTest extends Fdispositivo {
	@Test
	public void testEncederAlCumplirseCondicionDada() {
		assertEquals("El actuador debe enviar la orden de encender la luz", true, LuzInteligente.encendido());
	}
}
