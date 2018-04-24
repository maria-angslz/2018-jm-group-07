package sge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Administrador {
	private String[] nombres;
	private String[] apellidos;
	private String domicilio;
	private LocalDate fechaDeAlta;
	private long id;

	public static Administrador nuevoAdministrador(String[] nombres, String[] apellidos, String domicilio) {
		return new Administrador(nombres, apellidos, domicilio, LocalDate.now());
	}

	public Administrador(String[] nombres, String[] apellidos, String domicilio, LocalDate fechaDeAlta) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.fechaDeAlta = fechaDeAlta;
	}

	public int antiguedad() {
		LocalDate now = LocalDate.now();
		return antiguedad(now);
	}

	public int antiguedad(LocalDate now) {
		return (int) ChronoUnit.MONTHS.between(fechaDeAlta, now);
	}

	public String nombre() {
		return String.join(" ", nombres);
	}
}
