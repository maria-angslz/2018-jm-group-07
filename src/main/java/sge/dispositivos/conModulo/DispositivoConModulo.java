package sge.dispositivos.conModulo;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import sge.dispositivos.estandar.*;
import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
@DiscriminatorValue(value = "ConModulo")
public class DispositivoConModulo extends DispositivoInteligente {
	
	@OneToOne
	private DispositivoEstandar dispositivoAsociado;
	
	public DispositivoConModulo(DispositivoEstandar dispositivoAsociado) {
		super(dispositivoAsociado.nombre(), dispositivoAsociado.consumoKWxHora(), 
				dispositivoAsociado.getTipo());
		this.dispositivoAsociado = dispositivoAsociado;
	}
	
	public String nombre() {
		return dispositivoAsociado.nombre();
	}
	
	public double consumoKWxHora() {
		return dispositivoAsociado.consumoKWxHora();
	}
	
	public DispositivoEstandar getDispositivoAsociado() {
		return dispositivoAsociado;
	}

}
