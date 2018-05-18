package sge.estados;
import sge.dispositivosInteligentes.DispositivoInteligente;

public class Apagado implements EstadoDispositivo {
	
	public boolean encendido() {
		return false;
	}
	
	public void apagarse(DispositivoInteligente disp) {
		
	}
	
	public void encenderse(DispositivoInteligente disp) {
		disp.cambiarEstado(new Encendido());
	}
	
	public void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp) {
		disp.cambiarEstado(new AhorroDeEnergia());
	}
	
}