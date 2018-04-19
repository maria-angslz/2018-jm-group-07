import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import sge.Administrador;

public class AdministradorTest {

    @Test
    public void testAntiguedadDeAdministradorRegistradoHaceDosMeses() {
    	LocalDate now = LocalDate.now();
    	Administrador admin = new Administrador(
    			new String[]{"Juan"}, new String[]{"Perez"}, 
    			"Belgrano 2251", now.minusMonths(2));
        assertEquals("Un administrador registrado hace dos meses debe tener antiguedad 2", 2, admin.antiguedad(now));
    }
	@Test
	public void testAntiguedadDeNuevoAdministradorEsCero() {
		LocalDate now = LocalDate.now();
		Administrador admin = new Administrador(
				new String[]{"Juan"}, new String[]{"Perez"}, 
				"Belgrano 2251", now);
	    assertEquals("Un nuevo administrador debe tener antiguedad 0", 0, admin.antiguedad(now));
	}

}