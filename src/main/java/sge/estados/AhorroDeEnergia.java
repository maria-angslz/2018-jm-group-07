package sge.estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
@DiscriminatorValue(value = "AhorroDeEnergia")
public class AhorroDeEnergia extends EstadoDispositivo {
	
	public boolean encendido() {
		return false;
	}
	
	public boolean modoAhorroDeEnergia() {
		return true;
	}
	
	public void apagarse(DispositivoInteligente disp) {
		disp.cambiarEstado(new Apagado());
		disp.getDispositivoFisico().apagar();
	}
	
	public void encenderse(DispositivoInteligente disp) {
		disp.cambiarEstado(new Encendido());
		disp.getDispositivoFisico().encender();
	}
	
	public void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp) {
		
	}

}
