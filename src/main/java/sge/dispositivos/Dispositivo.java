package sge.dispositivos;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import sge.SuperClase;

@MappedSuperclass
public abstract class Dispositivo extends SuperClase
{
	protected String nombre;
	protected double consumoKWxHora;

	@Enumerated(EnumType.ORDINAL)
	protected TipoDeDispositivo tipo;
	

	public abstract double consumoKWxHora();
//	protected double minimo;
//	protected double maximo;
	
	
	public abstract double consumoMensual();
	public Double getMinimo() {
		return (double) tipo.getMinimo();
	}
	public Double getMaximo() {
		return (double) tipo.getMaximo();
	}
	public boolean mismoModelo(Dispositivo otro) {
		return otro.tipo.getMinimo() == tipo.getMinimo() &&
			otro.tipo.getMaximo() == tipo.getMaximo() &&
			otro.consumoKWxHora() == consumoKWxHora() &&
			otro.getClass() == getClass();
	}
	
	public String getNombre() {
		return nombre;
	}
	
}
