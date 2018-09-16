package sge;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Transient;

@Entity
public class Documento {
	@Id
	private int numero;
	
	@Transient
	private TipoDocumento tipo;

	public Documento() {
		super();
	}
	
	public Documento(int numero, TipoDocumento tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}
}
