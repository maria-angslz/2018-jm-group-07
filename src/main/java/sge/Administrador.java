package sge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Administrador {
	private String nombreYApellido;
	private String domicilio;
	private LocalDate fechaDeAlta;
	private long id;
	private String email;
	private int pass;

	public static Administrador nuevoAdministrador(String nombreYApellido, String domicilio) {
		return new Administrador(nombreYApellido, domicilio, LocalDate.now());
	}

	public Administrador(String nombres, String domicilio, LocalDate fechaDeAlta) {
		this.nombreYApellido = nombres;
		this.domicilio = domicilio;
		this.fechaDeAlta = fechaDeAlta;
	}
	
	public String email() {
		return email;
	}

	public Integer pass() {
		return pass;
	}

	public int antiguedad() {
		LocalDate now = LocalDate.now();
		return antiguedad(now);
	}

	public int antiguedad(LocalDate now) {
		return (int) ChronoUnit.MONTHS.between(fechaDeAlta, now);
	}

	public String getNombre() {
		return nombreYApellido;
	}
	
	public String getEmail() {
		return email;
	}
	
}
