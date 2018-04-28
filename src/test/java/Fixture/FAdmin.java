package Fixture;

import java.time.LocalDate;

import org.junit.Before;

import sge.Administrador;

public class FAdmin {
	protected Administrador adminViejo;
	protected Administrador adminNuevo;
	protected LocalDate now;
	
	@Before
	public void init() {
		now = LocalDate.now();
		adminViejo = new Administrador( "Juan Perez" , "Belgrano 2251",now.minusMonths(2));
		adminNuevo = new Administrador( "Juan Perez", "Belgrano 2251",now);
	}
}
