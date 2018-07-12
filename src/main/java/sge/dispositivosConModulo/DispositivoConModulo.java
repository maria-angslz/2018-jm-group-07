package sge.dispositivosConModulo;
import sge.dispositivosEstandar.*;
import sge.dispositivosInteligentes.DispositivoInteligente;

public class DispositivoConModulo extends DispositivoInteligente {
	
	private DispositivoEstandar dispositivoAsociado;
	
	public DispositivoConModulo(DispositivoEstandar dispositivoAsociado) {
		super(dispositivoAsociado.nombre(), dispositivoAsociado.consumoKWxHora(), 
				dispositivoAsociado.getMaximo(), dispositivoAsociado.getMinimo());
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
