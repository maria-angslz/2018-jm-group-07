package sge.dispositivosConModulo;
import sge.dispositivosEstandar.*;
import sge.dispositivosInteligentes.DispositivoInteligente;

public class DispositivoConModulo extends DispositivoInteligente {
	
	private DispositivoEstandar dispositivoAsociado;
	
	public DispositivoConModulo(String nombre, double consumoKWxHora, DispositivoEstandar dispositivoAsociado) {
		super(nombre, consumoKWxHora);
		this.dispositivoAsociado = dispositivoAsociado;
	}
	
	public DispositivoEstandar getDispositivoAsociado() {
		return dispositivoAsociado;
	}

}
