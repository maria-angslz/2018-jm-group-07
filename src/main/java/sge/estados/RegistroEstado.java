package sge.estados;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
public class RegistroEstado extends SuperClase{
	
	//@ManyToOne
	//private DispositivoInteligente Dispositivo;

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
		//this.Dispositivo = unIdDispositivo;
		this.NuevoEstado = unIdEstado;	
		this.fechaCambio = unaFechaCambio;
	}
	/*
	public void registrarCambio(){
		em.createQuery("INSERT INTO registroestado" + 
			"VALUES (:idDisp , :idEstado, fechaCambio);").setParameter("idDisp", this.idDispositivo).setParameter("idEstado",this.idNuevoEstado).setParameter("fechaCambio",this.fechaCambio);
	}
	*/
	
	/*public DispositivoInteligente getDispositivo() {
		return Dispositivo;
	}*/

	public EstadoDispositivo getNuevoEstado() {
		return NuevoEstado;
	}

	public Date getFechaCambio() {
		return fechaCambio;
	}
		
}
