import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AdministradorTest extends Fixture.FAdmin {

	@Test
	public void testAntiguedadDeAdministradorRegistradoHaceDosMeses() {
		assertEquals("Un administrador registrado hace dos meses debe tener antiguedad 2", 2, adminViejo.antiguedad(now));
	}

	@Test
	public void testAntiguedadDeNuevoAdministradorEsCero() {
		assertEquals("Un nuevo administrador debe tener antiguedad 0", 0, adminNuevo.antiguedad(now));
	}

}