package sge.estados;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import sge.SuperClase;
import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
@DiscriminatorColumn(name = "estado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EstadoDispositivo extends SuperClase {

	public int getId() {
		return id;
	}

	public abstract boolean encendido();

	public boolean getEncendido() {
		return encendido();
	}

	public abstract boolean modoAhorroDeEnergia();

	public abstract void apagarse(DispositivoInteligente disp);

	public abstract void encenderse(DispositivoInteligente disp);

	public abstract void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp);
}
