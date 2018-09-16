package sge;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Documento {
	@Id
	private int numero;
	
	private TipoDocumento tipo;

	public Documento() {
		super();
	}
	
	public Documento(int numero, TipoDocumento tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}
}
