package sge;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Documento{
	
	@Id
	private int numero;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoDocumento tipo;

	public Documento() {
		super();
	}
	public int numero() {
		return numero;
	}
	
	public Documento(int numero, TipoDocumento tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}
}
