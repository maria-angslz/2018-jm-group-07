package sge.estados;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import sge.dispositivos.inteligentes.DispositivoInteligente;

@Entity
@DiscriminatorColumn(name = "estado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EstadoDispositivo{  
	
	@Id @GeneratedValue
	private int id;
	
	public int getId() {
		return id;
	}
	public abstract boolean encendido();
	public abstract boolean modoAhorroDeEnergia();
	public abstract void apagarse(DispositivoInteligente disp);
	public abstract void encenderse(DispositivoInteligente disp);
	public abstract void entrarEnModoAhorroDeEnergia(DispositivoInteligente disp);
}
