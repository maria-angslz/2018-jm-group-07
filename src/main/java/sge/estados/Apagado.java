package sge.estados;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
@DiscriminatorValue(value = "Apagado")
public class Apagado extends EstadoDispositivo {

	public String toString() {
		return "Apagado";
	}

	public boolean encendido() {
		return false;
	}

	public boolean modoAhorroDeEnergia() {
		return false;
	}

	public void apagarse(DispositivoInteligente disp) {

	}

	public void encenderse(DispositivoInteligente disp) {
		disp.cambiarEstado(new Encendido());
		disp.getDispositivoFisico().encender();
	}

	public void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp) {
		disp.cambiarEstado(new AhorroDeEnergia());
		disp.getDispositivoFisico().entrarEnModoAhorroDeEnergia();
	}

}
