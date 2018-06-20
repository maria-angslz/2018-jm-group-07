package sge.estados;

import sge.dispositivosInteligentes.DispositivoInteligente;

public class Encendido implements EstadoDispositivo {
	
	public boolean encendido() {
		return true;
	}
	
	public void apagarse(DispositivoInteligente disp) {
		disp.cambiarEstado(new Apagado());
		disp.getDispositivoFisico().apagar();
	}
	
	public void encenderse(DispositivoInteligente disp) {
		
	}
	
	public void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp) {
		disp.cambiarEstado(new AhorroDeEnergia());
		disp.getDispositivoFisico().entrarEnModoAhorroDeEnergia();
	}
	
}
