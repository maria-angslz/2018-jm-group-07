package sge.estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
@DiscriminatorValue(value = "Encendido")  
public class Encendido extends EstadoDispositivo {

	public String toString() {
		return "Encendido";
	}
	
	public boolean encendido() {
		return true;
	}
	
	public boolean modoAhorroDeEnergia() {
		return false;
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
