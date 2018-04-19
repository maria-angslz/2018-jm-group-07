package SGE;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Administrador {
	String[] nombres;
	String[] apellidos;
	String domicilio;
	LocalDate fechaDeAlta;
	long id;
	
	Administrador(String[] nombres, String[] apellidos, String domicilio) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		fechaDeAlta = LocalDate.now();
	}
	public long antiguedad() {
		LocalDate now = LocalDate.now();
		return ChronoUnit.MONTHS.between(fechaDeAlta, now);
	}
}
