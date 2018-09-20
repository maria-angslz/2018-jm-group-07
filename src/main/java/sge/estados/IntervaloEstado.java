package sge.estados;

import java.util.Date;

public class IntervaloEstado {
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public EstadoDispositivo getEstado() {
		return estado;
	}
	public void setEstado(EstadoDispositivo estado) {
		this.estado = estado;
	}
	public IntervaloEstado(Date inicio, Date fin, EstadoDispositivo estado) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.estado = estado;
	}
	Date inicio;
	Date fin;
	EstadoDispositivo estado;
}
