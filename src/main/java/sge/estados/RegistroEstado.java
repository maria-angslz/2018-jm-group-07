package sge.estados;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
public class RegistroEstado extends SuperClase
{

	@OneToOne(cascade = {CascadeType.PERSIST})
	private EstadoDispositivo NuevoEstado;
	
	private Date fechaCambio;
	
	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public RegistroEstado() {
		super();
	}
	
	public RegistroEstado(DispositivoInteligente unIdDispositivo, EstadoDispositivo unIdEstado, Date unaFechaCambio) {
		this.NuevoEstado = unIdEstado;	
		this.fechaCambio = unaFechaCambio;
	}
	

	public EstadoDispositivo getNuevoEstado() {
		return NuevoEstado;
	}

	public Date getFechaCambio() {
		return fechaCambio;
	}
		
}
