package sge.dispositivos.conModulo;
import sge.dispositivos.estandar.*;
import sge.dispositivos.inteligentes.DispositivoInteligente;

public class DispositivoConModulo extends DispositivoInteligente {
	
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
