package sge.estados;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import sge.dispositivos.inteligentes.DispositivoInteligente;

public class IntervaloEstado {
	Date inicio;
	Date fin;
	EstadoDispositivo estado;

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
	
	public Double getHoras() {
		long diferencia = fin.getTime() - inicio.getTime();
		
		double segundos =  diferencia /1000.0;
		
		return segundos / 3600.0;
		
		//return (double) TimeUnit.HOURS.convert(diferencia, TimeUnit.MILLISECONDS);
		//no usamos convert(..) porque redondea para abajo y perdemos precision
	}
	
}
