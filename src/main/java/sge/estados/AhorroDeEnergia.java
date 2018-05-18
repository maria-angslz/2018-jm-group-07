package sge.estados;

import sge.dispositivosInteligentes.DispositivoInteligente;

public class AhorroDeEnergia implements EstadoDispositivo {
	
	public boolean encendido() {
		return false;
	}
	
	public void apagarse(DispositivoInteligente disp) {
		disp.cambiarEstado(new Apagado());
	}
	
	public void encenderse(DispositivoInteligente disp) {
		disp.cambiarEstado(new Encendido());
	}
	
	public void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp) {
		
	}

}
